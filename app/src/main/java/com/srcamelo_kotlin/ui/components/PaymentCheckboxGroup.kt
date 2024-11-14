package com.srcamelo_kotlin.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

@Composable
fun PaymentCheckboxGroup(
    paymentMethods: MutableMap<String, MutableState<Boolean>>
) {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(26.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Selecione as formas de pagamento:",
            fontFamily = Montserrat, fontWeight = FontWeight.Medium, color = DarkOrange
        )
        Row(horizontalArrangement = Arrangement.spacedBy(44.dp)){
            Column (verticalArrangement = Arrangement.spacedBy(26.dp)){
                PaymentCheckBox(checked = paymentMethods["dinheiro"]?.value ?:false,
                    title="Dinheiro", icon = painterResource(id = R.drawable.dinheiro_icon),
                    onCheckedChange =  { checked -> paymentMethods["dinheiro"]?.value = checked })
                PaymentCheckBox(checked = paymentMethods["credito"]?.value?:false,
                    title="Crédito" ,icon = painterResource(id = R.drawable.card_icon),
                    onCheckedChange =  { checked -> paymentMethods["credito"]?.value = checked })
            }
            Column(verticalArrangement = Arrangement.spacedBy(26.dp)){
                PaymentCheckBox(checked = paymentMethods["debito"]?.value?:false,
                    title="Débito",icon = painterResource(id = R.drawable.card_icon),
                    onCheckedChange =  { checked -> paymentMethods["debito"]?.value = checked })

                PaymentCheckBox(checked = paymentMethods["pix"]?.value?:false,
                    title="Pix", icon = painterResource(id = R.drawable.pix_icon),
                    onCheckedChange =  { checked -> paymentMethods["pix"]?.value = checked })
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun PreviewGroup(){
    SrCamelo_KotlinTheme {
        PaymentCheckboxGroup(
            paymentMethods = mutableMapOf(Pair("dinheiro", mutableStateOf(false)),Pair("debito", mutableStateOf(false)), Pair("credito", mutableStateOf(false)), Pair("pix", mutableStateOf(false)))
        )
    }
}