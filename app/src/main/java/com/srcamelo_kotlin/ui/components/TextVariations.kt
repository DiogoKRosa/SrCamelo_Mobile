package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.Green

@Composable
fun SectionTitle(
    name: String,
    modifier: Modifier = Modifier
){
    Text(name,
        style = TextStyle(
            fontFamily = Montserrat,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = DarkOrange
        ),
        modifier = modifier
    )
}

@Composable
fun BoldOrangeTitle(
    title: String,
    modifier: Modifier = Modifier
){
    Text(title,
        modifier = modifier,
        style = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = DarkOrange
        )
    )
}

@Composable
fun CardText(
    text: String,
    modifier: Modifier = Modifier,
    size: Int = 12
){
    Text(text,
        style = TextStyle(
            fontFamily = Montserrat,
            fontSize = size.sp,
            fontWeight = FontWeight.Normal,
        ),
        modifier = modifier
    )
}

@Composable
fun OrangeCardText(
    name: String,
    modifier: Modifier = Modifier
){
    Text(name,
        style = TextStyle(
            fontFamily = Montserrat,
            color = DarkOrange,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        ),
        modifier = modifier)
}

@Composable
fun  NormalGreenText(
    text: String,
    modifier: Modifier = Modifier,
    size: Int = 12
){
    Text(text,
        style = TextStyle(
            fontFamily = Montserrat,
            color = Green,
            fontWeight = FontWeight.Normal,
            fontSize = size.sp
        ),
        modifier = modifier)
}

@Preview
@Composable
private fun TextVariationsPreview(){
    val text = "Teste"
    Column() {
        BoldOrangeTitle(text)
        CardText(text)
        OrangeCardText(text)
        NormalGreenText(text)
    }
}