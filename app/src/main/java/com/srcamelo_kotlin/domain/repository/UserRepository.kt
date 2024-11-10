package com.srcamelo_kotlin.domain.repository

import com.srcamelo_kotlin.model.UserModel
import com.srcamelo_kotlin.network.Resource

interface UserRepository{
    suspend fun createClient(user: UserModel): Resource<Unit>
}