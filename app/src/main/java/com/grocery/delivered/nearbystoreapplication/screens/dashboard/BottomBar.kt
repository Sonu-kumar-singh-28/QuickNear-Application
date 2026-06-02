package com.grocery.delivered.nearbystoreapplication.screens.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grocery.delivered.nearbystoreapplication.R

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun PrepareBottomBarMenu(): List<BottomMenuItem> {

    return listOf(
        BottomMenuItem(
            label = "Home",
            icon = painterResource(R.drawable.btn_1)
        ),
        BottomMenuItem(
            label = "Support",
            icon = painterResource(R.drawable.btn_2)
        ),
        BottomMenuItem(
            label = "Wishlist",
            icon = painterResource(R.drawable.btn_3)
        ),
        BottomMenuItem(
            label = "Profile",
            icon = painterResource(R.drawable.btn_4)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun BottomBar() {

    val bottomItemMenuList = PrepareBottomBarMenu()
    val context = LocalContext.current

    var selected by remember {
        mutableStateOf("Home")
    }

    BottomAppBar(
        backgroundColor = colorResource(R.color.black3),
        elevation = 6.dp
    ) {

        bottomItemMenuList.forEach { item ->

            BottomNavigationItem(
                selected = selected == item.label,
                onClick = {

                    selected = item.label

                    Toast.makeText(
                        context,
                        item.label,
                        Toast.LENGTH_SHORT
                    ).show()
                },
                icon = {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            painter = item.icon,
                            contentDescription = item.label,
                            tint = colorResource(R.color.white),
                            modifier = Modifier
                                .size(22.dp)
                        )

                        Text(
                            text = item.label,
                            fontSize = 12.sp,
                            color = colorResource(R.color.white),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            )
        }
    }
}