package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.srcamelo_kotlin.R

@Composable
fun AccountImage(
    imageUrl: String = ""
){
    AsyncImage(
        model = imageUrl,
        contentDescription = "Imagem",
        placeholder = painterResource(R.drawable.placeholder_logo_black),
        error= painterResource(R.drawable.placeholder_logo_black),
        modifier = Modifier
            .size(69.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
private fun PreviewImage(){
    AccountImage()
}