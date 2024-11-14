package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.srcamelo_kotlin.ui.components.BackTopAppBar
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle

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
            Column(modifier = Modifier.fillMaxWidth().verticalScroll(scrollState).padding(innerpadding)) {

            }
        }
    )
}