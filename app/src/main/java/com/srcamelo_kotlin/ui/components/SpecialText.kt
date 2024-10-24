package com.srcamelo_kotlin.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange

@Composable
fun SpecialText(
    text: String,
    onClick: () -> Unit = {}
){
    TextButton(onClick = {onClick()}) {
        Text(text = text,
            fontFamily = Montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = DarkOrange,
            textDecoration = TextDecoration.Underline)
    }
}