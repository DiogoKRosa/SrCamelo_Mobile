package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

@Composable
fun PaymentCheckBox(
    modifier: Modifier,
    title: String = "",

){
    Row {
        Checkbox(checked = , onCheckedChange = )
        Text(title, )
    }
}

@Preview
@Composable
private fun PreviewPayment(){
    SrCamelo_KotlinTheme {
        PaymentCheckBox(title = "Dinheiro")
    }
}