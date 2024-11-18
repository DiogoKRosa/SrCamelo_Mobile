package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("token_type")
    val tokenType: String = "",
    @SerializedName("userType")
    val userType: String = "",
    @SerializedName("user_id")
    val userId: String = "",
    @SerializedName("firstAccess")
    val firstAccess: Boolean = false
)
