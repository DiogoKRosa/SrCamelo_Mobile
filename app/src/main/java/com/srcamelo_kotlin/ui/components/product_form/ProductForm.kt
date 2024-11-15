package com.srcamelo_kotlin.ui.components.product_form

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.ui.components.CreateIconButton
import com.srcamelo_kotlin.ui.components.DeleteIconButton
import com.srcamelo_kotlin.ui.components.DropDownInputLine
import com.srcamelo_kotlin.ui.components.EditIconButton
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

                var isDropDownExpanded by remember { mutableStateOf(false) }
                var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
                val categoryList = listOf("Salgado", "Doce", "Bijuteria", "Vestimentas")

                DropDownInputLine(
                    modifier = Modifier.width(139.dp),
                    isDropDownExpanded = isDropDownExpanded,
                    openDropDown = { isExpanded -> isDropDownExpanded = isExpanded },
                    onDismissRequest = { isDropDownExpanded = false },
                    onSelectNewValue = { index ->
                        selectedItemIndex = index
                        isDropDownExpanded = false
                    },
                    itemPosition = selectedItemIndex,
                    list = categoryList
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            DeleteIconButton(onClick = onCleanInput)
        }
        Spacer(modifier = Modifier.height(15.dp))
        CreateIconButton(onClick = onSubmit)
    }
}

@Composable
fun ProductFormReadOnly(
    id: String? = null,
    image: Uri? = null,
    name: String? = null,
    value: String? = null,
    description: String? = null,
    category: String? = null,
    editClick: () -> Unit = {},
    deleteClick: () -> Unit = {},
){
    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductFormImageBoxOnlyRead(
                image = image,
            )
            Spacer(modifier =Modifier.width(18.dp))

            Column{
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "Nome",
                    value = name?:"",
                    readOnly = true
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "R$ 0,00",
                    value = value?:"",
                    readOnly = true
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "Descrição",
                    value = description?:"",
                    readOnly = true
                )

                var isDropDownExpanded by remember { mutableStateOf(false) }
                var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
                val categoryList = listOf("Salgado", "Doce", "Bijuteria", "Vestimentas")

                DropDownInputLine(
                    modifier = Modifier.width(139.dp),
                    isDropDownExpanded = isDropDownExpanded,
                    openDropDown = {},
                    onDismissRequest = {},
                    onSelectNewValue = {},
                    itemPosition = selectedItemIndex,
                    list = categoryList
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                EditIconButton(onClick = editClick)
                DeleteIconButton(onClick = deleteClick)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewProductForm(){
    ProductForm()
}