package com.srcamelo_kotlin.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.ui.components.BackTopAppBar
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.components.NewVendorBannerPhoto
import com.srcamelo_kotlin.ui.theme.LightOrange

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewVendorHomeScreen(){
    Scaffold(
        containerColor = LightOrange,
        topBar = { BackTopAppBarWithTitle()},
        content = {innerpadding ->
            Column(modifier = Modifier.padding(innerpadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
                NewVendorBannerPhoto()
                Spacer(modifier = Modifier.height(38.dp))
                InputLine(placeholder = "Nome Fantasia")
            }
        },
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewVendorScreen(){
    NewVendorHomeScreen()
}