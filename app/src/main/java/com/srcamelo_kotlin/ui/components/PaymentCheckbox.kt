package com.srcamelo_kotlin.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
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
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.Green
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun PaymentCheckBox(
    modifier: Modifier = Modifier,
    title: String = "",
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit = {},
    icon: Painter
){
    Row (verticalAlignment = Alignment.CenterVertically){
        IconButton(onClick = {onCheckedChange(!checked)},
            modifier = Modifier.width(70.dp)
                .height(63.dp)
                .background(if(checked){Green}else{White},RoundedCornerShape(20.dp))) {
            androidx.compose.material3.Icon(painter = icon, contentDescription = "", tint = if(checked){White}else{Green})
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = title, color = Green, fontFamily = Montserrat, fontWeight = FontWeight.Normal)
    }
}

@Preview
@Composable
private fun PreviewPayment(){
    SrCamelo_KotlinTheme {
        Column {
            PaymentCheckBox(title = "Dinheiro",
                checked = false, icon = painterResource(id = R.drawable.dinheiro_icon))
            PaymentCheckBox(title = "Dinheiro",
                checked = true, icon = painterResource(id = R.drawable.dinheiro_icon))
        }
    }
}