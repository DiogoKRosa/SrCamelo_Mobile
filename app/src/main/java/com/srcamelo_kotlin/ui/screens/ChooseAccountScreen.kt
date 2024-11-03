package com.srcamelo_kotlin.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.SpecialText
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

@Composable
fun ChooseAccountScreen(
    onClickClientForm: () -> Unit = {},
    onClickVendorForm: () -> Unit = {},
    onClickLogin: () -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = LightOrange)
            .fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)

        Spacer(modifier = Modifier.height(83.dp))

        ButtonWhite(title="Consumidor", onClick=onClickClientForm)

        Spacer(modifier = Modifier.height(50.dp))

        ButtonWhite(title="Vendedor", onClick=onClickVendorForm)

        Spacer(modifier = Modifier.height(35.dp))

        SpecialText(text = "Já possuo uma conta", onClick=onClickLogin)
}
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun PreviewScreen(){
    SrCamelo_KotlinTheme {
        Scaffold {
            ChooseAccountScreen()
        }
    }
}