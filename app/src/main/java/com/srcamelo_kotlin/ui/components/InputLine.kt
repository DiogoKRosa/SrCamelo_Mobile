package com.srcamelo_kotlin.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

@Composable
fun InputLine(
    placeholder: String,
    modifier: Modifier = Modifier
){
    TextField(value = "", onValueChange = {},
        label = { Text(placeholder, fontFamily = Montserrat, fontWeight = FontWeight.Medium) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = DarkOrange,
            unfocusedIndicatorColor = DarkOrange
        ),
        

        )
}

@Preview(showSystemUi = true)
@Composable
fun InputPreview(){
    SrCamelo_KotlinTheme {
        InputLine("Email")
    }
}