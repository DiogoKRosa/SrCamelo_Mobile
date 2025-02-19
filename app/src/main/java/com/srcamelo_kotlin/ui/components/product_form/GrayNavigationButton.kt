package com.srcamelo_kotlin.ui.components.product_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.fonts.Montserrat

@Composable
fun GrayNavigationButton(
    title: String = "Teste",
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(9),
        colors = ButtonColors(
            containerColor = Color(0x10000000),
            contentColor = Color(0xFF333333),
            disabledContainerColor = Color(0x04000000),
            disabledContentColor = Color(0x04000000)
        )
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Text(title, fontSize = 14.sp, fontFamily = Montserrat, fontWeight = FontWeight.SemiBold)

            Icon(painter = painterResource(R.drawable.goback_white),
                contentDescription = "Seta",
                tint = Color(0x25000000),
                modifier = Modifier.rotate(180f))
        }
    }
}

@Preview
@Composable
private fun PreviewGrayButton(){
    GrayNavigationButton()
}