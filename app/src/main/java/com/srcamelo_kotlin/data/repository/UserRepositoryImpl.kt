package com.srcamelo_kotlin.data.repository

import com.srcamelo_kotlin.domain.repository.UserRepository
import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApi
import retrofit2.HttpException

class UserRepositoryImpl(
    private val apiService: SrcameloApi
): UserRepository{
    override suspend fun createClient(user: User): Resource<Unit>{
        return try{
            val response = apiService.retrofitService.createUser(user)
            Resource.Success(Unit)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        }
    }
}