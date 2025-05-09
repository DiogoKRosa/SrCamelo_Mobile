package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun ProductCard(
    baseUrl: String = "",
    imageUri: String = "",
    name: String = "",
    price: String = "",
    description: String = ""
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(Color(0, 0, 0, 10), RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Row{
            Box(
                modifier = Modifier
                    .size(142.dp, 92.dp)
                    .background(White, RoundedCornerShape(10.dp))
            ) {
                if (imageUri.isNotEmpty()) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "Product Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
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

            Spacer(modifier = Modifier.width(11.dp))
            Column(
                modifier = Modifier.width(186.dp)
            ) {
                Text(
                    name,
                    fontFamily = Montserrat,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    "Preço: R${'$'}$price",
                    fontFamily = Montserrat,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    description,
                    fontFamily = Montserrat,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}