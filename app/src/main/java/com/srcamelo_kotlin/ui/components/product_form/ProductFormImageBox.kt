package com.srcamelo_kotlin.ui.components.product_form

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun ProductFormImageBox(
    image: Uri? = null,
    onClick : () -> Unit = {}
) {
    Box(modifier = Modifier.size(123.dp)
        .background(White, RoundedCornerShape(10.dp))
        .border(2.dp, Color(0,0,0,20), RoundedCornerShape(10.dp))
        .clickable { onClick() }){
        if(image != null && !image.equals(Uri.EMPTY)){
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = rememberAsyncImagePainter(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        }else{
            Image(painter = painterResource(
                id = R.drawable.placeholder_logo_black),
                contentDescription = "placeholder",
                modifier = Modifier.fillMaxSize().padding(10.dp),
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center)
        }
    }
}

@Composable
fun ProductFormImageBoxOnlyRead(
    baseUrl: String = "http://10.0.2.2:8000",
    image: String? = null,
) {
    Box(modifier = Modifier.size(123.dp)
        .background(White, RoundedCornerShape(10.dp))
        .border(2.dp, Color(0,0,0,20), RoundedCornerShape(10.dp))){
        if(image != null){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$baseUrl/$image")
                    .crossfade(true)
                    .build(),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop
            )
        }else{
            Image(painter = painterResource(
                id = R.drawable.placeholder_logo_black),
                contentDescription = "placeholder",
                modifier = Modifier.fillMaxSize().padding(10.dp),
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center)
        }
    }
}

@Preview
@Composable
private fun PreviewProductBox(){
    ProductFormImageBox()
}