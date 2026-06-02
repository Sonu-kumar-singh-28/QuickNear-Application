package com.grocery.delivered.nearbystoreapplication.screens.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.grocery.delivered.nearbystoreapplication.R
import com.grocery.delivered.nearbystoreapplication.domain.BannerModel
import com.grocery.delivered.nearbystoreapplication.domain.CategoryModel
import com.grocery.delivered.nearbystoreapplication.repository.DashBoardRepository

@Composable
fun DashboardScreen(
    onCategoryClick: (id: String, title: String) -> Unit
) {

    val repository = remember {
        DashBoardRepository()
    }

    val categories = remember {
        mutableStateListOf<CategoryModel>()
    }

    val banners = remember {
        mutableStateListOf<BannerModel>()
    }

    var showCategoryLoading by remember {
        mutableStateOf(true)
    }

    var showBannerLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        repository.loadCategory().observeForever { list ->

            categories.clear()
            categories.addAll(list)

            showCategoryLoading = false
        }
    }


    // Load Banners
    LaunchedEffect(Unit) {

        repository.loadBanner().observeForever { list ->

            banners.clear()
            banners.addAll(list)

            showBannerLoading = false
        }
    }

    Scaffold(
        containerColor = colorResource(R.color.black2),
        bottomBar = {
            BottomBar()
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            item {
                TopBar()
            }

            item {
                CategorySection(
                    categories = categories,
                    showCategoryLoading = showCategoryLoading,
                    onCategoryClick = { id, title ->
                        onCategoryClick(id, title)
                    }
                )
            }

            item { Banner(banners, showBannerLoading)}
        }
    }
}