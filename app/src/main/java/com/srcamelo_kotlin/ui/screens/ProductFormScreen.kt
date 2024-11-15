package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.DropDownInputLine
import com.srcamelo_kotlin.ui.components.product_form.ProductForm
import com.srcamelo_kotlin.ui.components.product_form.ProductFormReadOnly

@Composable
fun ProductFormScreen(
    onClickBack: () -> Unit = {}
){
    Scaffold(
        topBar = { BackTopAppBarWithTitle(
            onClickBack = onClickBack,
            title = "Produtos"
        )},
        content = { innerpadding ->
            val scrollState = rememberScrollState()
            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(innerpadding)) {

                ProductForm()

                ProductFormReadOnly()


            }
        }
    )
}