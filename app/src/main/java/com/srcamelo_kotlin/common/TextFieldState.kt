package com.srcamelo_kotlin.common

import androidx.compose.runtime.MutableState

data class TextFieldState(
    val text: String = "",
    val error : String? = null
)
