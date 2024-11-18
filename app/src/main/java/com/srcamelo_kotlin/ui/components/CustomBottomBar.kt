package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomBar(
    homeClick: () -> Unit = {},
    cartClick: () -> Unit = {},
    balloonClick: () -> Unit = {},
    profileClick: () -> Unit = {}
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(54.dp)
        .background(DarkOrange, RoundedCornerShape(28.dp))){
        Row(modifier = Modifier.fillMaxWidth().align(Alignment.Center).padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                onClick = homeClick,
                colors = IconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Gray
                )
            ) {
                Icon(painter = painterResource(R.drawable.home_icon), contentDescription = "Home")
            }
            IconButton(
                onClick = cartClick,
                colors = IconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Gray
                )
            ) {
                Icon(painter = painterResource(R.drawable.cart_icon), contentDescription = "Home")
            }
            IconButton(
                onClick = balloonClick,
                colors = IconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Gray
                )
            ) {
                Icon(painter = painterResource(R.drawable.baloon_icon), contentDescription = "Home")
            }
            IconButton(
                onClick = profileClick,
                colors = IconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Gray
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.profile_icon),
                    contentDescription = "Home"
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBottom(){
    CustomBottomBar()
}