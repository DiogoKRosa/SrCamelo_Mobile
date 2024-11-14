package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.Green
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun ButtonWhite(
    modifier: Modifier = Modifier,
    title: String = "Botão",
    onClick: () -> Unit = {}
){
    Button(onClick = { onClick() },
        modifier = modifier
            .width(190.dp)
            .height(58.dp)
            .border(1.dp, Color(0, 0, 0, 20), RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(title, fontFamily = Montserrat, fontWeight = FontWeight.Medium, fontSize = 16.sp,color = Green)
    }
}

@Composable
fun SmallButtonWhite(
    modifier: Modifier = Modifier,
    title: String = "Botão",
    onClick: () -> Unit = {}
){
    Button(onClick = onClick,
        modifier = modifier
            .width(160.dp)
            .height(49.dp)
            .border(1.dp, Color(0, 0, 0, 20), RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(title, fontFamily = Montserrat, fontWeight = FontWeight.Medium, fontSize = 15.sp,color = Green)
    }
}

@Composable
fun IconButtonWhite(
    modifier : Modifier = Modifier,
    title: String = "",
    onClick: () -> Unit = {},
    icon: Painter = painterResource(id = R.drawable.logo)
){
    Box(
        modifier = modifier
        .background(White, RoundedCornerShape(20.dp))
        .wrapContentWidth()
        .height(70.dp)
            .border(1.dp,Color(0, 0, 0, 20), RoundedCornerShape(20.dp))
            .clickable { onClick() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ){
            Spacer(modifier = Modifier.width(20.dp))
            Image(painter = icon, contentDescription = "")
            Spacer(modifier = Modifier.width(20.dp))
            Text(title, fontFamily = Montserrat, fontWeight = FontWeight.Medium, fontSize = 15.sp,color = Green)
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun PreviewButton(){
    SrCamelo_KotlinTheme {
        ButtonWhite(title="Login", onClick = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun PreviewSmallButton(){
    SrCamelo_KotlinTheme {
        SmallButtonWhite(title="Adicionar Foto", onClick = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun PreviewIconButton(){
    SrCamelo_KotlinTheme {
        IconButtonWhite(title = "Cadastrar produtos")
    }
}

