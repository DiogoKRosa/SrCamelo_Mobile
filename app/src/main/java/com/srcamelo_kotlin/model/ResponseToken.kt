package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class ResponseToken(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: TokenModel
)
