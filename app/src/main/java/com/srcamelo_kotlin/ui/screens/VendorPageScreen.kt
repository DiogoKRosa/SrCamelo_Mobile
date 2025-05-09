package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.SrCameloScreens
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.model.Id
import com.srcamelo_kotlin.model.ProductModel
import com.srcamelo_kotlin.model.UserModel
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.CustomBottomBar
import com.srcamelo_kotlin.ui.components.PaymentTypeCart
import com.srcamelo_kotlin.ui.components.ProductCard
import com.srcamelo_kotlin.ui.components.TopAppBarWithTitle
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.White
import com.srcamelo_kotlin.ui.viewModel.ProductViewModel
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel

@Composable
fun VendorPageScreen(
    uid: String,
    homeClick: () -> Unit,
    cartClick: () -> Unit = {},
    balloonClick: () -> Unit = {},
    profileClick: () -> Unit = {},
    userViewModel: UsersViewModel = hiltViewModel(),
    productViewModel: ProductViewModel = hiltViewModel(),
    dataStoreManager: DataStoreManager,
    onClickLocation: () -> Unit = {},
    onClickBuy: () -> Unit = {},
    navController: NavController
) {
    val loginId by dataStoreManager.getUserId().collectAsState(initial = "")
    val user by userViewModel.userObj.observeAsState()
    LaunchedEffect(loginId){
        if (loginId.isNotBlank()) {
            userViewModel.getUserById(uid)
        }
    }

    Scaffold(
        topBar = { TopAppBarWithTitle(title = user?.name ?:"") },
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
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(White)
            ) {
                if (user != null && user?.image.toString().isNotEmpty()) {
                    AsyncImage(
                        model = "${BuildConfig.BASE_URL}${user!!.image}",
                        contentDescription = "Banner Image",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(
                            id = R.drawable.placeholder_logo_black
                        ),
                        contentDescription = "placeholder",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentScale = ContentScale.Inside,
                        alignment = Alignment.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))
            Text(
                "Telefone: ${user?.telephone}",
                fontFamily = Montserrat,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "E-mail: ${user?.email}",
                fontFamily = Montserrat,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            val products by productViewModel.products.observeAsState(emptyList())
            LaunchedEffect(loginId){
                if(loginId.isNotBlank()) {
                    productViewModel.getProductsFromVendor(uid)
                }
            }

            Spacer(modifier = Modifier.height(68.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Produtos Disponíveis",
                    fontFamily = Montserrat,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkOrange,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 36.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.height(300.dp)){
                    if(products.isNotEmpty()){
                        LazyColumn {
                            items(products) { product ->
                                ProductCard(
                                    name = product.name,
                                    price = product.price.toString(),
                                    description = product.description,
                                    imageUri = "${BuildConfig.BASE_URL}${product.image}"
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(31.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Formas de Pagamento",
                    fontFamily = Montserrat,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkOrange,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 36.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    user?.paymentMethods?.forEach { paymentMethod ->
                        when(paymentMethod){
                            "dinheiro" -> PaymentTypeCart(
                                icon = painterResource(R.drawable.dinheiro_icon),
                                type = paymentMethod
                            )
                            "debito" -> PaymentTypeCart(
                                icon = painterResource(R.drawable.card_icon),
                                type = paymentMethod
                            )
                            "credito" -> PaymentTypeCart(
                                icon = painterResource(R.drawable.card_icon),
                                type = paymentMethod
                            )
                            "pix" -> PaymentTypeCart(
                                icon = painterResource(R.drawable.pix_icon),
                                type = paymentMethod
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            ButtonWhite(title = "Localizar", onClick = onClickLocation)
            ButtonWhite(title = "Pedir", onClick = {navController.navigate(SrCameloScreens.ChooseProduct.name + "/${uid}")})
        }
    }
}