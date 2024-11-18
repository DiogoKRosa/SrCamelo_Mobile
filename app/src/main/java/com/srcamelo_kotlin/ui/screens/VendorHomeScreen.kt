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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.CustomBottomBar
import com.srcamelo_kotlin.ui.components.PaymentTypeCart
import com.srcamelo_kotlin.ui.components.ProductCard
import com.srcamelo_kotlin.ui.components.TopAppBarWithTitle
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun VendorHomeScreen(
    onClickBack: () -> Unit = {}
) {
    Scaffold(
        topBar = { TopAppBarWithTitle(title = "José Lanches") },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                CustomBottomBar()
            }
        }
    ) { innerPadding ->
        val baseUrl = ""
        val bannerImage = "https://www.minuano.com.br/mediafiles/img_conteudos/266/1635192340.jpg"

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(White)
            ) {
                if (bannerImage.isNotEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("$baseUrl$bannerImage")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Product Image",
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
                "Telefone: (11) 11111-9999",
                fontFamily = Montserrat,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "E-mail: jose.lanches@gmail.com.br",
                fontFamily = Montserrat,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

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
                LazyColumn {
                    item {
                        ProductCard(
                            name = "Dogão",
                            price = "9.00",
                            description = "Dogão com salsicha e mostarda",
                            imageUri = "https://www.minuano.com.br/mediafiles/img_conteudos/266/1635192340.jpg"
                        )
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
                    PaymentTypeCart(icon = painterResource(R.drawable.dinheiro_icon), type = "Dinheiro")
                    PaymentTypeCart(icon = painterResource(R.drawable.card_icon), type = "Débito")
                    PaymentTypeCart(icon = painterResource(R.drawable.card_icon), type = "Crédito")
                    PaymentTypeCart(icon = painterResource(R.drawable.pix_icon), type = "Pix")
                }
            }

        }
    }
}