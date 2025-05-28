package com.srcamelo_kotlin.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.model.ProductInvoiceModel
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.CardText
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.Gray
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.White
import com.srcamelo_kotlin.ui.viewModel.InvoiceViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


@Composable
fun InvoiceButton(){
    Button(onClick = {},
        modifier = Modifier.size(147.dp, 32.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonColors(containerColor = White, contentColor = Color(0xFF5CAC0E), disabledContentColor = Gray, disabledContainerColor = Color(0xFF5CAC0E))
    ) {
        Text("Cancelar", fontFamily = Montserrat, fontWeight = FontWeight.Normal, fontSize = 10.sp)
    }
}

@Composable
fun InvoiceClientCard(
    id: String = "",
    status: String = "",
    name: String = "",
    date: String = "",
    total: Double = 0.0,
    tel: String = "",
    productList: List<ProductInvoiceModel> = emptyList()
){
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier.fillMaxWidth().animateContentSize().border(0.5.dp, color = Color(0xFFCEDCA7),
        shape = RoundedCornerShape(10.dp)),
        colors = CardColors(
            containerColor = Color(0x04000000),
            contentColor = Color.DarkGray,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color(0x04000000)),
        shape = RoundedCornerShape(10.dp),
    ){
        Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)){
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                    CardText("Id: $id", size=14)
                    Text(status, fontFamily = Montserrat, fontWeight = FontWeight.Normal, fontSize = 12.sp, color = Color(0xFFFF6723))
                }
                CardText(date, size = 12)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                CardText("Vendedor: $name", size=12)
                CardText("Total: R$ $total")
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                CardText("Tel: $tel")
                Text(if(expanded) "Detalhes <" else "Detalhes >", fontFamily = Montserrat, fontWeight = FontWeight.Normal, fontSize = 12.sp, color = Color(0xFF5CAC0E),
                    modifier = Modifier.clickable { expanded = !expanded})
            }
        }
        AnimatedVisibility(visible = expanded) {
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)){
                    CardText("Pedido", modifier = Modifier.padding(bottom = 4.dp))
                    productList.forEach{ product ->
                        CardText("${product.productName} - ${product.productQtd} un - R$ ${product.productPrice}")
                    }
                }
                Spacer(modifier = Modifier.height(22.dp))
                Column{
                    InvoiceButton(text = "Cancelar")
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun InvoiceClientScreen(
    onClickBack: () -> Unit = {},
    dataStoreManager: DataStoreManager,
    invoiceViewModel: InvoiceViewModel = hiltViewModel()
){
    val loginId by dataStoreManager.getUserId().collectAsState("")
    val invoiceList by invoiceViewModel.invoiceList.observeAsState(emptyList())

    LaunchedEffect(loginId){
        invoiceViewModel.getInvoices(loginId)
    }

    Scaffold(
        topBar = { BackTopAppBarWithTitle(onClickBack = onClickBack, title = "Pedidos")},
        containerColor = LightOrange
    ) { innerpadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerpadding).padding(top = 45.dp)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(invoiceList){ index, invoice ->
                val datetime = OffsetDateTime.parse(invoice.datetime, DateTimeFormatter.ISO_DATE_TIME)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))

                InvoiceClientCard(
                    id = (index+1).toString(),
                    status = invoice.invoiceStatus,
                    name = invoice.vendorName,
                    date = datetime,
                    total = invoice.invoiceTotal,
                    tel = invoice.vendorNumber,
                    productList = invoice.productsList
                )
            }
        }
    }
}

/*
@Preview
@Composable
private fun InvoiceClientScreenPreview(){
    InvoiceClientScreen()
}
*/