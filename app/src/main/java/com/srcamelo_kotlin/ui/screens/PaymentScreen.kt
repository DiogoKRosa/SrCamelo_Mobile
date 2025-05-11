package com.srcamelo_kotlin.ui.screens

import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.model.InvoiceModel
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.BigIconButton
import com.srcamelo_kotlin.ui.components.BoldOrangeTitle
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.CouponTextField
import com.srcamelo_kotlin.ui.components.RegularBlackSubTitle
import com.srcamelo_kotlin.ui.components.SemiBoldBlackSubTitle
import com.srcamelo_kotlin.ui.components.SemiBoldOrangeSubTitle
import com.srcamelo_kotlin.ui.components.SemiBoldOrangeTitle
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.viewModel.InvoiceViewModel
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel
import java.text.NumberFormat


@Composable
fun PaymentScreen(
    uid: String,
    onClickBack: () -> Unit = {},
    onClickPayment: () -> Unit = {},
    invoiceViewModel: InvoiceViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel()
){
    val formatter = NumberFormat.getCurrencyInstance(java.util.Locale("pt", "BR"))
    val invoice = invoiceViewModel.invoiceToSend

    Scaffold(
        topBar = { BackTopAppBarWithTitle(title = "Pagamento", onClickBack = onClickBack) },
        containerColor = LightOrange
    ) { innerpadding ->
        Column (modifier = Modifier.padding(innerpadding).fillMaxWidth().padding(horizontal = 23.dp). padding(top=42.dp)){
            Row( horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()){
                BoldOrangeTitle(title = "Total")
                SemiBoldOrangeTitle(text = formatter.format(invoice?.invoiceTotal))
            }
            val products = if(invoice != null){
                invoice.productsList
            }else{
                emptyList()
            }
            LazyColumn(modifier = Modifier.padding(top = 10.dp), verticalArrangement = Arrangement.spacedBy(8.dp) ) {
                items(products){ item ->
                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)){
                        SemiBoldBlackSubTitle(text="${item.productQtd}x ${item.productName}")
                        SemiBoldBlackSubTitle(text=formatter.format(item.productPrice))
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

                val vendorUser = usersViewModel.vendorUser.value?.paymentMethods?: emptyList()

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(top = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    items(vendorUser){ item ->
                        Box(modifier = Modifier.wrapContentSize()){
                            if(invoice != null){
                                if(item == "pix"){
                                    BigIconButton(text = item, icon= painterResource(R.drawable.pix_icon), onClick = {
                                        invoice.paymentType = "pix"
                                        onClickPayment()
                                    })
                                }else if(item == "debito"){
                                    BigIconButton(text = item, icon= painterResource(R.drawable.card_icon), onClick = {
                                        invoice.paymentType = "debito"
                                        onClickPayment()
                                    })
                                }else if(item=="credito"){
                                    BigIconButton(text = item, icon= painterResource(R.drawable.card_icon), onClick = {
                                        invoice.paymentType = "credito"
                                        onClickPayment()
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun PreviewPaymentScreen(){
    PaymentScreen()
}
*/
