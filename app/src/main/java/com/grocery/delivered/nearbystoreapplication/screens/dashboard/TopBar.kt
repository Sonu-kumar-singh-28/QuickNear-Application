package com.grocery.delivered.nearbystoreapplication.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grocery.delivered.nearbystoreapplication.R

@Preview(showBackground = true)
@Composable
fun TopBar() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        val (title1, title2, profile, box) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .padding(16.dp)
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "Good Morning, Sonu",
            fontSize = 20.sp,
            color = colorResource(id = R.color.gold),
            modifier = Modifier.constrainAs(title1) {
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(profile.start, margin = 12.dp)
                top.linkTo(profile.top)
                bottom.linkTo(profile.bottom)
                width = androidx.constraintlayout.compose.Dimension.fillToConstraints
            }
        )

        Text(
            text = "What are You Doing Today?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.gold),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(title2) {
                    start.linkTo(parent.start)
                    top.linkTo(profile.bottom, margin = 12.dp)
                }
        )

        // ===== BOX =====
        ConstraintLayout(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.black3))
                .constrainAs(box) {
                    top.linkTo(title2.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {

            val (icon1, icon2, balance, amount, reward, wallet, line1) = createRefs()

            Image(
                painter = painterResource(R.drawable.wallet),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(icon1) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    }
            )

            Image(
                painter = painterResource(R.drawable.medal),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(icon2) {
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    }
            )

            Text(
                text = "Wallet",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.constrainAs(wallet) {
                    top.linkTo(icon1.top)
                    bottom.linkTo(icon1.bottom)
                    start.linkTo(icon1.end, margin = 8.dp)
                }
            )

            Text(
                text = "Reward",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.constrainAs(reward) {
                    top.linkTo(icon2.top)
                    bottom.linkTo(icon2.bottom)
                    start.linkTo(icon2.end, margin = 8.dp)
                }
            )

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .padding(vertical = 16.dp)
                    .background(colorResource(R.color.grey))
                    .constrainAs(line1) {
                        centerTo(parent)
                    }
            )

            Text(
                text = "Balance",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.constrainAs(balance) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(line1.end, margin = 16.dp)
                }
            )

            Text(
                text = "150.00 USD",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.constrainAs(amount) {
                    top.linkTo(balance.bottom, margin = 8.dp)
                    start.linkTo(balance.start)
                }
            )
        }
    }
}