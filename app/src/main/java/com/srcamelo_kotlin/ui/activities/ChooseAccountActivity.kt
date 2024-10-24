package com.srcamelo_kotlin.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

class ChooseAccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SrCamelo_KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChooseAccountScreen()
                }
            }
        }
    }
}

@Composable
fun ChooseAccountScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = LightOrange)
            .fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)

        Spacer(modifier = Modifier.height(83.dp))

        ButtonWhite("Consumidor")

        Spacer(modifier = Modifier.height(50.dp))

        ButtonWhite("Vendedor")

        Spacer(modifier = Modifier.height(35.dp))

        Text(text = "Já possuo uma conta",
            fontFamily = Montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = DarkOrange,
            textDecoration = TextDecoration.Underline)
}
}

@Preview(showSystemUi = true)
@Composable
fun PreviewScreen(){
    SrCamelo_KotlinTheme {
        Scaffold { innerpadding ->
            ChooseAccountScreen()
        }
    }
}