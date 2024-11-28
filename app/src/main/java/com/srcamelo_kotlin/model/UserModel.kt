package com.srcamelo_kotlin.model


import com.google.gson.annotations.SerializedName
import java.io.Serial

data class UserModel(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("cpf")
    val cpf: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("_id")
    val id: Id?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("telephone")
    val telephone: String?,
    @SerializedName("uf")
    val uf: String?,
    @SerializedName("userType")
    val userType: String?,
    @SerializedName("establishment")
    val establishment: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("paymentMethods")
    val paymentMethods: List<String>?,
    @SerializedName("firstAccess")
    val firstAccess: Boolean?,
)