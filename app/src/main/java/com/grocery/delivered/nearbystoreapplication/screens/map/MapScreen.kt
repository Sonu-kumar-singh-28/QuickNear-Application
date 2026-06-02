package com.grocery.delivered.nearbystoreapplication.screens.map

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

import com.grocery.delivered.nearbystoreapplication.R
import com.grocery.delivered.nearbystoreapplication.domain.StoreModel
import com.grocery.delivered.nearbystoreapplication.screens.results.ItemsNearest

@Composable
fun MapScreen(storeModel: StoreModel) {

    val context = LocalContext.current

    val latitude = storeModel.Latitude ?: 0.0
    val longitude = storeModel.Longitude ?: 0.0

    val location = LatLng(latitude, longitude)

    val cameraPositionState = rememberCameraPositionState()

    // 🔥 IMPORTANT: force camera update
    LaunchedEffect(location) {
        cameraPositionState.position =
            CameraPosition.fromLatLngZoom(location, 15f)
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (map, detail) = createRefs()

        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(map) { centerTo(parent) },
            cameraPositionState = cameraPositionState
        ) {

            Marker(
                state = MarkerState(position = location),
                title = "Store Location"
            )
        }

        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .background(
                    color = colorResource(R.color.black3),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
                .constrainAs(detail) {
                    bottom.linkTo(parent.bottom)
                    centerHorizontallyTo(parent)
                }
        ) {

            item {
                ItemsNearest(storeModel)
            }

            item {
                Button(
                    onClick = {
                        val phoneNumber = storeModel.Call ?: ""

                        val dialIntent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:$phoneNumber")
                        )
                        context.startActivity(dialIntent)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(R.color.gold)
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Call to STORE",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}