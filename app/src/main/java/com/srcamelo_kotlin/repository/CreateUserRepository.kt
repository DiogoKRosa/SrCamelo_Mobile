package com.srcamelo_kotlin.repository

import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApi
import retrofit2.HttpException

class CreateUserRepository(){
    suspend fun createClient(user: User): Resource<Unit>{
        return try{
            val response = SrcameloApi.retrofitService.createUser(user)
            Resource.Success(Unit)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        }
    }
}