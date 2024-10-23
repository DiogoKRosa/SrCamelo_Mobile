package com.srcamelo_kotlin.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

@Composable
fun InputLine(
    placeholder: String,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    var textState by remember { mutableStateOf(TextFieldValue(value))}
    TextField(value = textState,
        onValueChange = {
            textState = it
            onValueChange(it.text)},
        label = { Text(placeholder, fontFamily = Montserrat, fontWeight = FontWeight.Medium) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = DarkOrange,
            unfocusedIndicatorColor = DarkOrange
        ),
        textStyle = TextStyle(fontSize = 16.sp, fontFamily = Montserrat, fontWeight = FontWeight.Medium),
        modifier = modifier
        )
}

@Preview(showBackground = true)
@Composable
fun InputPreview(){
    SrCamelo_KotlinTheme {
        InputLine(placeholder = "Email")
    }
}