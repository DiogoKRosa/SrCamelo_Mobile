package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.Green
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun ButtonWhite(
    title: String = "Botão",
    modifier: Modifier = Modifier,
){
    Button(onClick = { /*TODO*/ },
        modifier = modifier
            .width(190.dp)
            .height(58.dp)
            .border(1.dp, Color(0,0,0,20), RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(title, fontFamily = Montserrat, fontWeight = FontWeight.Medium, fontSize = 16.sp,color = Green)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButton(){
    SrCamelo_KotlinTheme {
        ButtonWhite("Login")
    }
}