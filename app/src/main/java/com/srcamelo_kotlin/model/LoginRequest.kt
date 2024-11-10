package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

class LoginRequest (
    @SerializedName("email")
    val email: Any?,
    @SerializedName("password")
    val password: Any?
)