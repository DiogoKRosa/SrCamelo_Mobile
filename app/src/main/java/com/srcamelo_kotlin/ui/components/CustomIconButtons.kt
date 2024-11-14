package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.Green
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun CreateIconButton(
    onClick: () -> Unit = {}
){
    IconButton(onClick = onClick,
        colors = IconButtonColors(
            containerColor = White,
            contentColor = Green,
            disabledContainerColor = White,
            disabledContentColor = Color.Gray
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.plus_icon),
            contentDescription = "Adicionar novo produto"
        )
    }
}

@Composable
fun DeleteIconButton(
    onClick: () -> Unit = {}
){
    IconButton(onClick = onClick,
        colors = IconButtonColors(
            containerColor = Color.Transparent,
            contentColor = DarkOrange,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.trash_icon),
            contentDescription = "Excluir produto"
        )
    }
}

@Composable
fun EditIconButton(
    onClick: () -> Unit = {}
){
    IconButton(onClick = onClick,
        colors = IconButtonColors(
            containerColor = Color.Transparent,
            contentColor = DarkOrange,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        )
    ) {
        Icon(
            imageVector = Icons.Filled.Create,
            contentDescription = "Editar produto"
        )
    }
}

@Preview
@Composable
private fun PreviewIconButtons(){
    Column {
        CreateIconButton()
        DeleteIconButton()
        EditIconButton()
    }
}