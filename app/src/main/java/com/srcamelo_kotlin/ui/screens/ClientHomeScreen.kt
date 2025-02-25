package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.ClientHomeTopBar
import com.srcamelo_kotlin.ui.components.CustomBottomBar
import com.srcamelo_kotlin.ui.components.SearchBar
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun SectionTitle(
    name: String,
    modifier: Modifier = Modifier
){
    Text(name,
        style = TextStyle(
            fontFamily = Montserrat,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = DarkOrange
        ),
        modifier = modifier
    )
}
@Composable
fun CardText(
    name: String,
    modifier: Modifier = Modifier
){
    Text(name,
        style = TextStyle(
            fontFamily = Montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        ),
        modifier = modifier
    )
}

@Composable
fun CategoryButton(
    name: String,
    icon: Int,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        IconButton(
            onClick = {},
            colors = IconButtonColors(
                containerColor = White,
                contentColor = DarkOrange,
                disabledContentColor = Color.Gray,
                disabledContainerColor = White
            ),
            modifier = Modifier.size(62.dp)
        ){
            Icon(painter = painterResource(icon),
                contentDescription = name)
        }
        CardText(name)
    }
}

@Composable
fun VendorCard(
    urlImage: String,
    vendorName: String,
    distance: String,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        ),
        modifier = modifier
    ){
        AsyncImage(
            model = urlImage,
            contentDescription = vendorName,
            placeholder = painterResource(R.drawable.placeholder_logo_black),
            error = painterResource(R.drawable.placeholder_logo_black),
            modifier = Modifier.size(120.dp, 70.dp).clip(RoundedCornerShape(10.dp))
                .background(White),
            contentScale = ContentScale.Crop
        )
        CardText(vendorName, modifier = Modifier.align(Alignment.CenterHorizontally))
        CardText(distance, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun ClientHomeScreen(
){
    Scaffold (
        topBar = { ClientHomeTopBar()},
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                CustomBottomBar(
                    homeClick = {},
                    cartClick = {},
                    balloonClick = {},
                    profileClick = {}
                )
            }
        },
        containerColor = LightOrange
    ){ innerpadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ){
            SearchBar()
            
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 33.dp).padding(top = 16.dp),
                horizontalAlignment = Alignment.Start
            ){
                SectionTitle("Filtrar por", modifier = Modifier.align(Alignment.Start))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    CategoryButton("Salgados", R.drawable.cachorro_quente)
                    CategoryButton("Doces", R.drawable.cupcake)
                    CategoryButton("Bebidas", R.drawable.bebida)
                    CategoryButton("Acessórios", R.drawable.colar)
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 33.dp).padding(top = 36.dp),
                horizontalAlignment = Alignment.Start
            ){
                SectionTitle("Localização", modifier = Modifier.padding(bottom = 10.dp))
                Box(
                    modifier = Modifier.size(326.dp, 180.dp).background(color = White, shape = RoundedCornerShape(20.dp))
                ){
                    Text("Mapa")
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 33.dp).padding(top = 36.dp),
                horizontalAlignment = Alignment.Start
            ){
                SectionTitle("Vendedores próximos de você")
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    data class Vendedor(val urlImage: String, val vendorName: String, val distance: String)
                    val vendedores = listOf(
                        Vendedor("", "José Lanches", "500m"),
                        Vendedor("", "Fruta Feira", "2km"),
                        Vendedor("", "Lica Doce", "2.2km"),
                    )
                    items(vendedores){ vendedor ->
                        VendorCard(urlImage = vendedor.urlImage,
                            vendorName = vendedor.vendorName,
                            distance = vendedor.distance)
                    }

                }

            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 33.dp).padding(top = 36.dp),
                horizontalAlignment = Alignment.Start
            ){
                SectionTitle("Histórico")
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                    data class Vendedor(val urlImage: String, val vendorName: String, val distance: String)
                    val vendedores = listOf(
                        Vendedor("", "José Lanches", "500m"),
                        Vendedor("", "Fruta Feira", "2km"),
                        Vendedor("", "Lica Doce", "2.2km"),
                    )
                    items(vendedores){ vendedor ->
                        VendorCard(urlImage = vendedor.urlImage,
                            vendorName = vendedor.vendorName,
                            distance = vendedor.distance)
                    }

                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewClientHomeScreen(){
    SrCamelo_KotlinTheme {
        ClientHomeScreen()
    }
}