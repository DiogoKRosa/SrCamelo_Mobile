package com.srcamelo_kotlin.model


import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("city")
    val city: Any?,
    @SerializedName("country")
    val country: Any?,
    @SerializedName("cpf")
    val cpf: Any?,
    @SerializedName("email")
    val email: Any?,
    @SerializedName("_id")
    val id: Id?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password")
    val password: Any?,
    @SerializedName("telephone")
    val telephone: Any?,
    @SerializedName("uf")
    val uf: Any?,
    @SerializedName("userType")
    val userType: Any?
)