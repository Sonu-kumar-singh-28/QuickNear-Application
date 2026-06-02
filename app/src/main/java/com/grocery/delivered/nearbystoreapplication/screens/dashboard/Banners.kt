package com.grocery.delivered.nearbystoreapplication.screens.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.grocery.delivered.nearbystoreapplication.domain.BannerModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlidingBanner(
    banners: List<BannerModel>
) {

    val pagerState = rememberPagerState(
        pageCount = { banners.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(banners[page].image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}



@Composable
fun Banner(banners: SnapshotStateList<BannerModel>, showBannerLoading: Boolean){
    if(showBannerLoading){
        Box(
            modifier = Modifier.fillMaxSize()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }else{
        SlidingBanner(banners = banners)
    }

}