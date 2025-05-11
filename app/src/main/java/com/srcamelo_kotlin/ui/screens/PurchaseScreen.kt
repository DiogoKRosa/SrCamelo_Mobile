package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.NumberText
import com.srcamelo_kotlin.ui.components.RegularBlackSubTitle
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.White
import com.srcamelo_kotlin.ui.viewModel.InvoiceViewModel
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel

@Composable
fun PaymentOrangeIcon(
    painter: Painter,
    text: String,

){
    Box(modifier = Modifier.size(120.dp)
        .background(color = DarkOrange, shape = RoundedCornerShape(30.dp)),
        contentAlignment = Alignment.Center){
        Column {
            Icon(painter = painter,
                tint = White, contentDescription = "pix", modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.height(10.dp))
            NumberText(text = text, color = White, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun PurchaseScreen(
    onClickBack: () -> Unit = {},
    onClickFinish: () -> Unit = {},
    invoiceViewModel: InvoiceViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel()
){
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val invoice = invoiceViewModel.invoiceToSend
    var paymentType = ""

    when(invoice?.paymentType){
        "pix" -> paymentType = "Pix"
        "debito" -> paymentType = "Débito"
        "credito" -> paymentType = "Crédito"
    }


    Scaffold(
        topBar = { BackTopAppBarWithTitle(title = paymentType, onClickBack = onClickBack) },
        containerColor = LightOrange
    ) { innerPadding ->
       Column( modifier = Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){
           RegularBlackSubTitle(text = "Forma de pagamento", size = 18, color = DarkOrange, modifier = Modifier.padding(top = 40.dp))
           RegularBlackSubTitle(text = "Total: R$${invoice?.invoiceTotal?:""}", modifier = Modifier.padding(top = 17.dp, bottom = 30.dp))
           if(invoice?.paymentType == "pix"){
               PaymentOrangeIcon(painter = painterResource(R.drawable.pix_icon), text = "Pix")
           }else if(invoice?.paymentType == "debito"){
               PaymentOrangeIcon(painter = painterResource(R.drawable.card_icon), text = "Débito")
           }else if (invoice?.paymentType == "credito"){
               PaymentOrangeIcon(painter = painterResource(R.drawable.card_icon), text = "Crédito")
           }
           Column(modifier = Modifier.fillMaxHeight(1f).padding(bottom = bottomPadding + 45.dp), verticalArrangement = Arrangement.Bottom) {
               ButtonWhite(title = "Finalizar Pedido",
                   onClick = {
                        if (invoice != null) {
                            invoiceViewModel.sendInvoice(invoice)
                            onClickFinish()
                        }
                   })
           }
       }
    }
}

@Preview
@Composable
fun PurchaseScreenPreview(){
    PurchaseScreen()
}