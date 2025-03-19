package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun CouponTextField(
    value: String = "",
){
    BasicTextField(
        value = value,
        onValueChange = {},
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .size(209.dp, 26.dp),
        textStyle = TextStyle(
            fontSize = 10.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxSize().background(White, RoundedCornerShape(5.dp))
                .border(width = 1.dp, color = Color(0,0,0,20), RoundedCornerShape(5.dp))) {
                if (value.isEmpty()) {
                    Text(
                        text = "Exemplo: CAMELO10",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF8899A6),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun CouponTextFieldPreview(){
    CouponTextField()
}