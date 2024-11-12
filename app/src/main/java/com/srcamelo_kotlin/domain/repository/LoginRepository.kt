package com.srcamelo_kotlin.domain.repository

import com.srcamelo_kotlin.model.UserModel
import com.srcamelo_kotlin.model.LoginRequest
import com.srcamelo_kotlin.model.TokenModel
import com.srcamelo_kotlin.network.Resource

interface LoginRepository {
    suspend fun login(user: LoginRequest): Resource<TokenModel>
}