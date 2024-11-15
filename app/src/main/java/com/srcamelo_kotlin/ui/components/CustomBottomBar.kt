package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srcamelo_kotlin.ui.theme.DarkOrange

@Composable
fun CustomBottomBar() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(54.dp)
        .background(DarkOrange, RoundedCornerShape(28.dp))){

    }
}

@Preview
@Composable
private fun PreviewBottom(){
    CustomBottomBar()
}