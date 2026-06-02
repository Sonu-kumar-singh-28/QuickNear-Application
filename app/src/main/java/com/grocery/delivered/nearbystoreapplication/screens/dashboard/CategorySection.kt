package com.grocery.delivered.nearbystoreapplication.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.grocery.delivered.nearbystoreapplication.R
import com.grocery.delivered.nearbystoreapplication.domain.CategoryModel

@Composable
fun CategoryItem(
    category: CategoryModel,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {

    Column(
        modifier = modifier
            .background(
                color = colorResource(R.color.black3),
                shape = RoundedCornerShape(13.dp)
            )
            .clickable { onItemClick() }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = category.ImagePath,
            contentDescription = null,
            modifier = Modifier.size(65.dp)
        )

        Text(
            text = category.Name,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun CategorySection(
    categories: SnapshotStateList<CategoryModel>,
    showCategoryLoading: Boolean,
    onCategoryClick: (id: String, title: String) -> Unit
) {

    Text(
        text = "Explore Stores",
        fontSize = 20.sp,
        color = colorResource(R.color.gold),
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    )

    if (showCategoryLoading) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

    } else {

        // 2 items per row
        val rows = categories.chunked(3)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {

            rows.forEach { row ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    row.forEach { categoryModel ->

                        CategoryItem(
                            category = categoryModel,
                            modifier = Modifier.weight(1f),
                            onItemClick = {
                                onCategoryClick(
                                    categoryModel.Id.toString(),
                                    categoryModel.Name
                                )
                            }
                        )
                    }

                    repeat(3 - row.size) {
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}