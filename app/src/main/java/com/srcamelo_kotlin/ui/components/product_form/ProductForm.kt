package com.srcamelo_kotlin.ui.components.product_form

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.ui.components.CreateIconButton
import com.srcamelo_kotlin.ui.components.DeleteIconButton
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.components.SmallInputLine

@Composable
fun ProductForm(
    id: String? = null,
    image: Uri? = null,
    name: String? = null,
    value: String? = null,
    description: String? = null,
    category: String? = null,
    onSubmit: () -> Unit = {},
    onImageClick: () -> Unit = {},
    onCleanInput: () -> Unit = {}
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductFormImageBox(
                image = image,
                onClick = onImageClick
            )
            Spacer(modifier =Modifier.width(18.dp))

            Column{
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "Nome",
                    value = name?:"",
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "R$ 0,00",
                    value = value?:"",
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "Descrição",
                    value = description?:"",
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "Categoria",
                    value = category?:"",
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            DeleteIconButton(onClick = onCleanInput)
        }
        Spacer(modifier = Modifier.height(15.dp))
        CreateIconButton(onClick = onSubmit)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewProductForm(){
    ProductForm()
}