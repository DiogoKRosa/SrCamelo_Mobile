package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.CustomBottomBar
import com.srcamelo_kotlin.ui.components.SemiBoldBlackSubTitle
import com.srcamelo_kotlin.ui.components.SemiBoldOrangeSubTitle
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun AnalyticsScreen(
    onClickBack: () -> Unit = {},
    onClickHome: () -> Unit = {},
    onClickCart: () -> Unit = {},
    onClickBalloon: () -> Unit = {},
    onClickProfile: () -> Unit = {}
){
    Scaffold(
        topBar = {BackTopAppBarWithTitle(onClickBack = onClickBack, title = "Análise de Vendas")},
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                CustomBottomBar(
                    homeClick = onClickHome,
                    cartClick = onClickCart,
                    balloonClick = onClickBalloon,
                    profileClick = onClickProfile
                )
            }
        },
        containerColor = LightOrange
    ) { innerpadding ->
        Column(modifier = Modifier.padding(innerpadding).padding(horizontal= 28.dp, vertical=30.dp).fillMaxSize()
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(34.dp)){
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth().background(color = White).padding(top = 22.dp, bottom = 38.dp, start=20.dp, end = 15.dp)){
                    SemiBoldOrangeSubTitle(text="Resumo de vendas", size = 20)
                    Spacer(modifier= Modifier.height(17.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                        Column {
                            SemiBoldOrangeSubTitle(text = "R$ 2.000,00", size = 20)
                            SemiBoldBlackSubTitle(text = "Vendidos em Abril", color = Color(0x37000000), size = 14)
                        }
                        Column{
                            SemiBoldOrangeSubTitle(text = "150 Produtos", size = 20)
                            SemiBoldBlackSubTitle(text="Quantidade", color = Color(0x37000000), size = 14)
                        }
                    }
                    Spacer(modifier=Modifier.height(17.dp))
                    Row{
                        Column {
                            SemiBoldOrangeSubTitle(text="HotDog", size = 20)
                            SemiBoldBlackSubTitle(text="Mais vendidos", color = Color(0x37000000), size = 14)
                        }
                    }
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier=Modifier.fillMaxWidth().background(White).padding(vertical=20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 25.dp)){
                        SemiBoldOrangeSubTitle(text ="Vendas por dia", size = 20)
                        SemiBoldBlackSubTitle(text="13/04 - 19/04", color = Color(0x37000000), size = 14)
                    }
                    AsyncImage(R.drawable.semana, contentDescription = "Semana", modifier = Modifier.size(305.dp, 160.dp))
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier=Modifier.fillMaxWidth().background(White).padding(vertical = 15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 10.dp)) {
                        SemiBoldOrangeSubTitle(text ="Vendas por local", size = 20)
                        SemiBoldBlackSubTitle(text="13/04 - 19/04", color = Color(0x37000000), size = 14)
                    }
                    AsyncImage(R.drawable.heatmap, contentDescription = "HeatMap", modifier = Modifier.size(305.dp, 160.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun AnalyticsScreenPreview(){
    AnalyticsScreen()
}