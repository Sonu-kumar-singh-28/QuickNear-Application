package com.grocery.delivered.nearbystoreapplication.screens.results

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grocery.delivered.nearbystoreapplication.R

@Preview(showBackground = true)
@Composable
fun Search() {

    var text by remember { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    TextField(
        value = text,
        onValueChange = {
            text = it
        },

        placeholder = {
            if (!isFocused && text.isEmpty()) {
                Text(
                    text = "Find Stores, Restaurants, Products...",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        },

        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search",
                modifier = Modifier.size(22.dp)
            )
        },

        interactionSource = interactionSource,

        shape = RoundedCornerShape(12.dp),

        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = R.color.black3),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            textColor = Color.White
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(56.dp)
    )
}