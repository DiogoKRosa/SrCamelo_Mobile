package com.srcamelo_kotlin.ui.activities

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SrCamelo_KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(color = LightOrange)
            .fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
        InputLine(placeholder = "Email")
        InputLine(placeholder = "Senha")
        Text(text = "Não possuo uma conta")
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    SrCamelo_KotlinTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            ){ innerPadding ->
            MainScreen(modifier = Modifier
                .fillMaxSize())
        }
    }
}