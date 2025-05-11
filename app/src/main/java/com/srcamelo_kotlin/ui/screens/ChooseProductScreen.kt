package com.srcamelo_kotlin.ui.screens

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.SrCameloScreens
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.model.InvoiceModel
import com.srcamelo_kotlin.model.ProductInvoiceModel
import com.srcamelo_kotlin.model.UserModel
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
import com.srcamelo_kotlin.ui.viewModel.InvoiceViewModel
import com.srcamelo_kotlin.ui.viewModel.ProductViewModel
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel
import kotlinx.coroutines.delay

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
    quantity: Int = 0,
    onQuantityChange: (Int) -> Unit
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
                if(quantity <= 0){
                    MiniMinusButton(enabled = false)
                }else{
                    MiniMinusButton(onClick = {
                        onQuantityChange(quantity-1)
                    })
                }
                NumberText(text=quantity.toString())

                MiniAddButton(onClick = {
                    onQuantityChange(quantity+1)
                })
            }
        }
    }
}

@Composable
fun ChooseProductScreen(
    uid: String = "",
    onClickBack: () -> Unit = {},
    onClickPay: () -> Unit = {},
    dataStoreManager: DataStoreManager,
    productViewModel: ProductViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
    invoiceViewModel: InvoiceViewModel = hiltViewModel(),
    navController: NavController
){
    val loginId by dataStoreManager.getUserId().collectAsState(initial = "")
    val products by productViewModel.products.observeAsState(emptyList())
    val clientUser by usersViewModel.clientUser.observeAsState()
    val vendorUser by usersViewModel.vendorUser.observeAsState()

    val productsQuantities = remember { mutableStateMapOf<String, Int>()}

    LaunchedEffect(loginId) {
        usersViewModel.getVendorById(uid)
        delay(3000)
        usersViewModel.getClientById(loginId)
        productViewModel.getProductsFromVendor(uid)
    }

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
                items(products){ product ->
                    val id = product.id?.oid
                    if( id != null){
                        val quantity = productsQuantities[id]
                        ChooseProductItem(image = "${BuildConfig.BASE_URL}${product.image}",
                            productName = product.name,
                            productPrice = product.price,
                            quantity = quantity?:0,
                            onQuantityChange = { newQty -> productsQuantities[id] = newQty}
                        )
                        HorizontalDivider(color= DarkOrange, thickness = 1.dp, modifier = Modifier.padding(vertical=17.dp))
                    }
                }
            }

            val total = products.sumOf { product ->
                val qty = productsQuantities[product.id?.oid] ?: 0
                product.price * qty
            }

            Text("Total:", modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = Montserrat, fontWeight = FontWeight.Normal, color = DarkOrange, fontSize = 16.sp)
            Text("R$ %.2f".format(total), modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = Montserrat, fontWeight = FontWeight.Normal, color = Gray, fontSize = 20.sp)
            ButtonWhite(title = "Pagamento", modifier = Modifier.align(Alignment.CenterHorizontally).padding(top=45.dp),
                onClick = {
                    if(clientUser != null && vendorUser != null){
                        val totalProducts = products.mapNotNull { product ->
                            val qtd = productsQuantities[product.id?.oid] ?: 0
                            if(qtd > 0){
                                product.id?.oid?.let {
                                    ProductInvoiceModel(
                                        productId = it,
                                        productName = product.name,
                                        productQtd = qtd,
                                        productPrice = product.price * qtd
                                    )
                                }
                            }else{
                                null
                            }
                        }
                        val invoice = InvoiceModel(
                            invoiceStatus = "pendente",
                            clientId = loginId,
                            clientName = clientUser!!.name?:"",
                            clientNumber = clientUser!!.telephone?:"",
                            vendorId = uid,
                            vendorName = vendorUser!!.name?:"",
                            vendorNumber = vendorUser!!.telephone?:"",
                            invoiceTotal = total,
                            productsList = totalProducts,
                            paymentType = ""
                        )

                            invoiceViewModel.invoiceToSend = invoice
                            navController.navigate(SrCameloScreens.ChoosePayment.name + "/${uid}")
                    }
                }
            )
        }
    }
}

/*
@Preview(showSystemUi = true)
@Composable
private fun PreviewChooseProductScreen(){
    SrCamelo_KotlinTheme {
        ChooseProductScreen()
    }
}
*/