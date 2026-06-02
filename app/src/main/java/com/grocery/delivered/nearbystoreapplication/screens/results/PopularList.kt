package com.grocery.delivered.nearbystoreapplication.screens.results

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.grocery.delivered.nearbystoreapplication.R
import com.grocery.delivered.nearbystoreapplication.domain.StoreModel

@Composable
fun ItemsPopular(
    item: StoreModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .width(140.dp)
            .background(
                color = colorResource(R.color.black3),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(8.dp)
    ) {

        AsyncImage(
            model = item.ImagePath,
            contentDescription = item.Title,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    colorResource(R.color.grey),
                    RoundedCornerShape(10.dp)
                ),
            contentScale = ContentScale.Crop
        )

        Text(
            text = item.Title,
            color = colorResource(R.color.white),
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

        Row(
            modifier = Modifier.padding(top = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = item.ShortAddress,
                color = colorResource(R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 6.dp),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun PopularSection(
    list: SnapshotStateList<StoreModel>,
    showPopularLoading: Boolean,
    onStoreClick: (StoreModel) -> Unit
) {

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 15.dp, bottom = 8.dp)
        ) {

            Text(
                text = "Popular Stores",
                color = colorResource(R.color.gold),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "See all",
                color = Color.White,
                fontSize = 16.sp,
                style = TextStyle(
                    textDecoration = TextDecoration.Underline
                )
            )
        }

        if (showPopularLoading) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = colorResource(R.color.gold)
                )
            }

        } else {

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp
                )
            ) {

                items(
                    count = list.size
                ) { index ->

                    val item = list[index]

                    ItemsPopular(
                        item = item,
                        onClick = {
                            onStoreClick(item)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemsPopularPreview() {

    val item = StoreModel(
        Id = 1,
        CategoryId = "1",
        Title = "Fresh Grocery Store",
        Latitude = 0.0,
        Longitude = 0.0,
        Address = "123 Main Street",
        Call = "9999999999",
        Activity = "Open",
        ShortAddress = "Main Street",
        Hours = "9 AM - 10 PM",
        ImagePath = ""
    )

    ItemsPopular(
        item = item,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PopularSectionPreview() {

    val stores = mutableStateListOf(
        StoreModel(
            Id = 1,
            Title = "Fresh Grocery",
            ShortAddress = "Main Street",
            ImagePath = ""
        ),
        StoreModel(
            Id = 2,
            Title = "City Mart",
            ShortAddress = "Downtown",
            ImagePath = ""
        ),
        StoreModel(
            Id = 3,
            Title = "Super Market",
            ShortAddress = "Central Road",
            ImagePath = ""
        )
    )

    PopularSection(
        list = stores,
        showPopularLoading = false,
        onStoreClick = {}
    )
}