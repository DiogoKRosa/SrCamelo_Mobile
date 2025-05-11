package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.NumberText
import com.srcamelo_kotlin.ui.components.SemiBoldOrangeSubTitle
import com.srcamelo_kotlin.ui.theme.LightOrange

@Composable
fun CompleteScreen(
    onClickBack: () -> Unit = {},
    onClickMap: () -> Unit = {},
    onClickMenu: () -> Unit = {}
){
    Scaffold(
        topBar = { BackTopAppBarWithTitle(title = "Finalizado", onClickBack = onClickBack) },
        containerColor = LightOrange
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize().padding(top = 113.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(model = R.drawable.mascote, contentDescription = "Mascote",
                modifier = Modifier.size(225.dp, 202.dp),
                contentScale = ContentScale.Fit)

            SemiBoldOrangeSubTitle(text = "Compra finalizada com sucesso!", modifier = Modifier.padding(top = 32.dp))

            NumberText(text = "Você já pode retirar o seu pedido com o vendedor. " +
                    "Clique no mapa para ver onde a loja está localizada",
                modifier = Modifier.width(321.dp).padding(top = 17.dp))

            Column(modifier = Modifier.padding(bottom = 69.dp).fillMaxHeight(1f), verticalArrangement = Arrangement.Bottom){
                ButtonWhite(title="Mapa", onClick = {onClickMap()})
                Spacer(modifier = Modifier.height(20.dp))
                ButtonWhite(title="Voltar para o menu", onClick = {onClickMenu()})
            }
        }
    }
}

@Preview
@Composable
fun PreviewCompleteScreen(){
    CompleteScreen()
}