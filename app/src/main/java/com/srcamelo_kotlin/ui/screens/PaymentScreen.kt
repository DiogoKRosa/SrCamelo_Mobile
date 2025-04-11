package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.BigIconButton
import com.srcamelo_kotlin.ui.components.BoldOrangeTitle
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.CouponTextField
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.Gray
import com.srcamelo_kotlin.ui.theme.LightOrange
import java.text.NumberFormat

@Composable
fun SemiBoldOrangeTitle(
    modifier: Modifier = Modifier,
    text: String,
    size: Int = 20
){
    Text(text, modifier = modifier,
        fontFamily = Montserrat, color = DarkOrange, fontSize = size.sp, fontWeight = FontWeight.SemiBold)
}

@Composable
fun SemiBoldBlackSubTitle(
    modifier: Modifier = Modifier,
    text: String,
    size: Int = 16
){
    Text(text, modifier = modifier, fontFamily = Montserrat, fontSize = size.sp,
        fontWeight = FontWeight.SemiBold, color = Gray)
}

@Composable
fun RegularBlackSubTitle(
    modifier: Modifier = Modifier,
    text: String,
    size: Int = 16
){
    Text(text, modifier = modifier, fontFamily = Montserrat, fontSize = size.sp,
        fontWeight = FontWeight.Normal, color = Gray)
}

@Composable
fun SemiBoldOrangeSubTitle(
    modifier: Modifier = Modifier,
    text: String,
    size: Int = 16
){
    Text(text = text, modifier=modifier, fontFamily = Montserrat, fontSize = size.sp,
        fontWeight = FontWeight.SemiBold, color = DarkOrange)
}

@Composable
fun PaymentScreen(
    onClickBack: () -> Unit = {},
    onClickDebit: () -> Unit = {},
    onClickCredit: () -> Unit = {},
    onClickPix: () -> Unit = {}
){
    val formatter = NumberFormat.getCurrencyInstance(java.util.Locale("pt", "BR"))
    Scaffold(
        topBar = { BackTopAppBarWithTitle(title = "Pagamento", onClickBack = onClickBack) },
        containerColor = LightOrange
    ) { innerpadding ->
        Column (modifier = Modifier.padding(innerpadding).fillMaxWidth().padding(horizontal = 23.dp). padding(top=42.dp)){
            Row( horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()){
                BoldOrangeTitle(title = "Total")
                SemiBoldOrangeTitle(text = formatter.format(15))
            }
            data class Invoice (val name: String, val qtd: Int, val price: Double)
            val list = listOf(
                Invoice("Dogão", 1, 9.00),
                Invoice("Laranjinha", 1, 6.00)
            )
            LazyColumn(modifier = Modifier.padding(top = 10.dp), verticalArrangement = Arrangement.spacedBy(8.dp) ) {
                items(list){ item ->
                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)){
                        SemiBoldBlackSubTitle(text="${item.qtd}x ${item.name}")
                        SemiBoldBlackSubTitle(text=formatter.format(item.price * item.qtd))
                    }
                }
            }

            HorizontalDivider(color= DarkOrange, thickness = 1.dp,
                modifier = Modifier.padding(vertical=17.dp))

            Column( horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp)){
                RegularBlackSubTitle(text = "Digite o cupom de desconto", size = 15)
                CouponTextField()
                ButtonWhite(title = "Aplicar Cupom")
            }

            HorizontalDivider(color= DarkOrange, thickness = 1.dp,
                modifier = Modifier.padding(vertical=17.dp))

            Column(modifier = Modifier.padding(horizontal = 8.dp)){
                SemiBoldOrangeSubTitle(text = "Pagamento")
                Spacer(modifier = Modifier.height(11.dp))
                RegularBlackSubTitle(text = "Escolher forma de Pagamento")

                data class Payment(val text: String, val painter:Painter)
                val items = listOf(
                    Payment("Débito", painterResource(R.drawable.card_icon)),
                    Payment("Crédito", painterResource(R.drawable.card_icon)),
                    Payment("Pix", painterResource(R.drawable.pix_icon))
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(top = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    items(items){ item ->
                        Box(modifier = Modifier.wrapContentSize()){
                            BigIconButton(text = item.text, icon = item.painter, onClick = {})
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPaymentScreen(){
    PaymentScreen()
}
