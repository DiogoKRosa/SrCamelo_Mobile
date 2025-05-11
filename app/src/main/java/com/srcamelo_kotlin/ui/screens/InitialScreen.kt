package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.SrCameloScreens
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import kotlinx.coroutines.delay

@Composable
fun InitialScreen(navController: NavController){
    LaunchedEffect(Unit){
        delay(500)
        navController.navigate(SrCameloScreens.Login.name)
    }
    Scaffold(
        containerColor = LightOrange
    ) { innerpadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerpadding), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            AsyncImage(model = R.drawable.logo, contentDescription = null, modifier = Modifier.size(211.dp, 150.dp))
            Spacer(modifier = Modifier.height(60.dp))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = DarkOrange)
            }
        }
    }
}