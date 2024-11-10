package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.components.InputLinePassword
import com.srcamelo_kotlin.ui.components.SpecialText
import com.srcamelo_kotlin.ui.theme.LightOrange

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSubmit: () -> Unit = {},
    onChooseAccountClick: () -> Unit = {}
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
        InputLinePassword(placeholder = "Senha", value = password, onValueChange = {password = it})

        Spacer(modifier = Modifier.height(54.dp))

        ButtonWhite(
            title = "Entrar",
            onClick = onLoginSubmit
        )

        Spacer(modifier = Modifier.height(35.dp))

        SpecialText(
            text = "Não possuo uma conta",
            onClick = onChooseAccountClick
        )
    }
}