package com.srcamelo_kotlin.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.SrCameloScreens
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopAppBar(
    onClickBack: () -> Unit = {}
){
    Box{
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = DarkOrange
            ),
            title = {},
            navigationIcon = {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 14.dp), contentAlignment = Alignment.Center){
                    IconButton(onClick = onClickBack) {
                        Image(painter = painterResource(id = R.drawable.goback_white),
                            contentDescription = "")
                    }
                }

            },
            modifier = Modifier.height(103.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .background(color = LightOrange)
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(100, 100))
            .border(BorderStroke(0.dp, White), shape = RoundedCornerShape(100, 100))){}
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopAppBarWithTitle(
    onClickBack: () -> Unit = {},
    title: String = ""
){
    Box{
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = DarkOrange
            ),
            title = {Text(text = title, color = White,
                fontFamily = Montserrat, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center))},
            navigationIcon = {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 14.dp), contentAlignment = Alignment.Center){
                    IconButton(onClick = onClickBack) {
                        Image(painter = painterResource(id = R.drawable.goback_white),
                            contentDescription = "")
                    }
                }

            },
            modifier = Modifier.height(103.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .background(color = LightOrange)
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(100, 100))
            .border(BorderStroke(0.dp, White), shape = RoundedCornerShape(100, 100))){}
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithTitle(
    title: String = ""
){
    Box{
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = DarkOrange
            ),
            title = {Text(text = title, color = White,
                fontFamily = Montserrat, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center))},
            modifier = Modifier.height(103.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .background(color = LightOrange)
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(100, 100))
            .border(BorderStroke(0.dp, White), shape = RoundedCornerShape(100, 100))){}
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewTopBar(){
    SrCamelo_KotlinTheme {
        Scaffold(
            topBar = {BackTopAppBarWithTitle(title = "Foto do Perfil")},
        ) {
        }
    }
}