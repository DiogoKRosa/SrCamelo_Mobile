package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun SearchBar(
    value: String = "",
    onValueChange: (String) -> Unit = {}
){
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .size(316.dp, 30.dp),
        textStyle = TextStyle(
            fontSize = 10.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxSize().background(White, RoundedCornerShape(10.dp))) {
                if (value.isEmpty()) {
                    Text(
                        text = "Pesquise aqui...",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF8899A6),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(painter = painterResource(R.drawable.lupa),
                        contentDescription = "Pesquisar",
                        tint = DarkOrange)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchBar(){
    Column{
        SearchBar()
    }
}