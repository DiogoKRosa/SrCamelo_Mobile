package com.srcamelo_kotlin.model


data class User(
    val userType: String,
    val name: String,
    val cpf: String,
    val email: String,
    val telephone: String,
    val password: String,
    val country: String,
    val uf: String,
    val city: String
)
