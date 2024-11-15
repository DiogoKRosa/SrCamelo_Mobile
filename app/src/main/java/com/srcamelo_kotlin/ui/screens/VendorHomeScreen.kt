package com.srcamelo_kotlin.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle

@Composable
fun VendorHomeScreen(
    onClickBack: () -> Unit = {}
) {
    Scaffold(
        topBar = { BackTopAppBarWithTitle(title = "José Lanches", onClickBack = onClickBack)},
        bottomBar = {}
    ){ innerPadding ->

    }
}