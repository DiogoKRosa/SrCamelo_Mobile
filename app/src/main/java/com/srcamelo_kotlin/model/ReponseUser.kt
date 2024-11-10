package com.srcamelo_kotlin.model


import com.google.gson.annotations.SerializedName

data class ReponseUser(
    @SerializedName("data")
    val `data`: UserModel?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)