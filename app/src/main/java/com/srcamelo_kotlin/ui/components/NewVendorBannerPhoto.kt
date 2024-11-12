package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun NewVendorBannerPhoto(
    modifier: Modifier = Modifier,
    cameraIntent: () -> Unit = {},
    image: String? = null
){
    Box(modifier = modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(LightOrange)){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(174.dp)
            .align(Alignment.TopCenter)
            .background(White)){

            if(image != null){
                Text(image)
            }else{
                //Text("Pré-visualização")
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