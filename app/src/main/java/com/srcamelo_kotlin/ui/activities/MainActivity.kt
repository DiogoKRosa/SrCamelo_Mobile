package com.srcamelo_kotlin.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.components.SpecialText
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SrCamelo_KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ClientFormScreen()
                //                    MainScreen(
//                        chooseAccount = {
//                            startActivity(
//                                Intent(
//                                    this,
//                                    ChooseAccountActivity::class.java
//                                )
//                            )
//                        }
//                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    chooseAccount: () -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(color = LightOrange)
            .fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)

        Spacer(modifier = Modifier.height(44.dp))

        var email by remember { mutableStateOf("") }
        InputLine(placeholder = "Email", value = email, onValueChange = {email = it})

        Spacer(modifier = Modifier.height(29.dp))

        var password by remember { mutableStateOf("") }
        InputLine(placeholder = "Senha", value = password, onValueChange = {password = it})

        Spacer(modifier = Modifier.height(54.dp))

        ButtonWhite("Entrar")
        Spacer(modifier = Modifier.height(35.dp))
        SpecialText(text = "Não possuo uma conta",
            onClick = {
                chooseAccount()
            })
    }
}

@Composable
fun ClientFormScreen(
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(color = LightOrange)
            .fillMaxSize()) {
        Text(text = "Cadastro")
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