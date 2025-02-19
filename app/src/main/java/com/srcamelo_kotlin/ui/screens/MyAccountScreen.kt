package com.srcamelo_kotlin.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.ui.components.AccountImage
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.CustomBottomBar
import com.srcamelo_kotlin.ui.components.product_form.GrayNavigationButton
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

@Composable
fun MyAccountScreen(
    homeClick: () -> Unit = {},
    cartClick: () -> Unit = {},
    balloonClick: () -> Unit = {},
    profileClick: () -> Unit = {},
){
    Scaffold(
        topBar = {BackTopAppBarWithTitle(title = "Minha Conta")},
        bottomBar = {
            Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp)
        ) {
            CustomBottomBar(
                homeClick = homeClick,
                cartClick = cartClick,
                balloonClick = balloonClick,
                profileClick = profileClick
            )
        }
        },
        containerColor = LightOrange
    ){ innerPadding ->
        Column (
            modifier = Modifier.fillMaxWidth().padding(innerPadding).padding(horizontal = 40.dp).padding(top = 37.dp)){
            Row (horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)){
                AccountImage()
                Text("José Lanches", fontFamily = Montserrat, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
             Column(modifier = Modifier.fillMaxWidth(),
                 verticalArrangement = Arrangement.spacedBy(17.dp)){
                 GrayNavigationButton("Vendas")
                 GrayNavigationButton("Dados Cadastrais")
                 GrayNavigationButton("Produtos")
                 GrayNavigationButton("Sair")
             }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
private fun PreviewMyAccountScreen(){
    SrCamelo_KotlinTheme {
        Scaffold {
            MyAccountScreen()
        }
    }
}