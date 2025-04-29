package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.BoldOrangeTitle
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.CardText
import com.srcamelo_kotlin.ui.components.NumberText
import com.srcamelo_kotlin.ui.components.OrangeCardText
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.Gray
import com.srcamelo_kotlin.ui.theme.LightGray
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun MiniAddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true
){
    IconButton(
        onClick = onClick,
        colors = IconButtonColors(
            containerColor = DarkOrange,
            contentColor = White,
            disabledContentColor = White,
            disabledContainerColor = LightGray
        ),
        enabled = enabled,
        modifier = modifier.size(20.dp)
    ) {
        Icon(painter = painterResource(R.drawable.mini_plus_icon), contentDescription = "Adicionar")
    }
}

@Composable
fun MiniMinusButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true
){
    IconButton(
        onClick = onClick,
        colors = IconButtonColors(
            containerColor = DarkOrange,
            contentColor = White,
            disabledContentColor = White,
            disabledContainerColor = LightGray
        ),
        enabled = enabled,
        modifier = modifier.size(20.dp)
    ) {
        Icon(painter = painterResource(R.drawable.mini_minus_icon), contentDescription = "Adicionar")
    }
}

@Composable
fun ChooseProductItem(
    modifier: Modifier = Modifier,
    image: String = "",
    productName: String = "Produto",
    productPrice: Double = 100.00,
    quantity: String = "0"
){
    Row {
        AsyncImage(model = image, contentDescription = "lanche",
            placeholder = painterResource(R.drawable.placeholder_logo_black),
            error= painterResource(R.drawable.placeholder_logo_black),
            modifier = modifier.size(141.dp, 92.dp).background(White, RoundedCornerShape(10.dp)).clip(
                RoundedCornerShape(10.dp)))
        Column(modifier = Modifier.padding(vertical = 10.dp).padding(start=10.dp)){
            CardText(productName)
            Spacer(modifier = Modifier.height(3.dp))
            OrangeCardText("R$$productPrice")
            Row(horizontalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.padding(top=20.dp)){
                MiniMinusButton(enabled = false)
                NumberText(text=quantity)
                MiniAddButton()
            }
        }
    }
}

@Composable
fun ChooseProductScreen(
    onClickBack: () -> Unit = {},
    onClickPay: () -> Unit = {}
){
    Scaffold(
        topBar = { BackTopAppBarWithTitle(title = "Carrinho", onClickBack = onClickBack)},
        containerColor = LightOrange
    ){ innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding).padding(horizontal = 29.dp, vertical = 42.dp)
        ){
            BoldOrangeTitle("Seu pedido")
            Spacer(modifier= Modifier.height(26.dp))
            LazyColumn {
                data class Product(val image:String, val name:String, val price:Double, val quantity: String)
                val products = listOf(
                    Product("", "Dogão", 9.00 , "0"),
                    Product("", "Pizza", 12.00 , "0"),
                    Product("", "Laranjinha", 9.00 , "0")
                )
                items(products){ product ->
                    ChooseProductItem(image = product.image, productName = product.name, productPrice = product.price, quantity = product.quantity)
                    HorizontalDivider(color= DarkOrange, thickness = 1.dp, modifier = Modifier.padding(vertical=17.dp))
                }
            }
            Text("Total:", modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = Montserrat, fontWeight = FontWeight.Normal, color = DarkOrange, fontSize = 16.sp)
            Text("R$ 15,00", modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = Montserrat, fontWeight = FontWeight.Normal, color = Gray, fontSize = 20.sp)
            ButtonWhite(title = "Pagamento", modifier = Modifier.align(Alignment.CenterHorizontally).padding(top=45.dp),
                onClick = onClickPay)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewChooseProductScreen(){
    SrCamelo_KotlinTheme {
        ChooseProductScreen()
    }
}