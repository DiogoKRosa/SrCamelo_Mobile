package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.CustomBottomBar
import com.srcamelo_kotlin.ui.theme.LightOrange

@Composable
fun PrivateChatScreen(
    uid: String? = "",
    onClickBack: () -> Unit = {}
){
    Scaffold(
        topBar = { BackTopAppBarWithTitle(onClickBack = onClickBack, title = "Conversas") },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                /*CustomBottomBar(
                    homeClick = onClickHome,
                    cartClick = onClickCart,
                    balloonClick = onClickBalloon,
                    profileClick = onClickProfile
                )*/
            }
        },
        containerColor = LightOrange,
        contentColor = LightOrange
    ) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){
            /*TODO*/
            Text(uid?:"Erro ao carregar a mensagem", color = Color.Black)

        }
    }
}