package com.srcamelo_kotlin.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun NewVendorBannerPhoto(
    modifier: Modifier = Modifier,
    cameraIntent: () -> Unit = {},
    image: Uri? = null
){

    Box(modifier = modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(LightOrange)){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(174.dp)
            .align(Alignment.TopCenter)
            .background(White),){

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
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.Center)
            }

        }
        SmallButtonWhite(title="Adicionar foto",
            onClick = cameraIntent,
            modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewBanner(){
    NewVendorBannerPhoto()
}