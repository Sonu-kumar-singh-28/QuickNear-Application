package com.grocery.delivered.nearbystoreapplication.screens.results

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun StoreDetails(
    item: StoreModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        Text(
            text = item.Title,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = item.ShortAddress,
                color = Color.LightGray,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 6.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = item.Address,
            color = Color.White,
            fontSize = 11.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = item.Activity,
            color = colorResource(R.color.gold),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Hours : ${item.Hours}",
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun StoreImage(
    item: StoreModel
) {
    AsyncImage(
        model = item.ImagePath,
        contentDescription = item.Title,
        modifier = Modifier
            .size(95.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                colorResource(R.color.grey),
                RoundedCornerShape(10.dp)
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ItemsNearest(
    item: StoreModel,
    onClick: (() -> Unit)? = null
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.black3),
                RoundedCornerShape(10.dp)
            )
            .padding(8.dp)
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
    ) {

        StoreImage(item)

        StoreDetails(
            item = item
        )
    }
}

@Composable
fun NearestList(
    list: SnapshotStateList<StoreModel>,
    showNearestLoading: Boolean,
    onStoreClick: (StoreModel) -> Unit
) {

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {

            Text(
                text = "Near Stores",
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

        if (showNearestLoading) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    color = colorResource(R.color.gold)
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
            ) {

                items(list) { item ->

                    ItemsNearest(
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
fun ItemsNearestPreview() {

    val item = StoreModel(
        Id = 1,
        Title = "Fresh Grocery Store",
        Address = "123 Main Street",
        ShortAddress = "Main Street",
        Activity = "Open Now",
        Hours = "9 AM - 10 PM",
        ImagePath = ""
    )

    ItemsNearest(
        item = item
    )
}

@Preview(showBackground = true)
@Composable
fun NearestListPreview() {

    val stores = mutableStateListOf(
        StoreModel(
            Id = 1,
            Title = "Fresh Grocery",
            Address = "Main Street",
            ShortAddress = "Main Street",
            Activity = "Open",
            Hours = "9 AM - 10 PM"
        ),
        StoreModel(
            Id = 2,
            Title = "City Mart",
            Address = "Downtown",
            ShortAddress = "Downtown",
            Activity = "Open",
            Hours = "8 AM - 11 PM"
        )
    )

    NearestList(
        list = stores,
        showNearestLoading = false,
        onStoreClick = {}
    )
}