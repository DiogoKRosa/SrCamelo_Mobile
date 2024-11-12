package com.srcamelo_kotlin.data.repository

import com.srcamelo_kotlin.domain.repository.UserRepository
import com.srcamelo_kotlin.model.UserModel
import com.srcamelo_kotlin.network.Resource
import okio.IOException
import retrofit2.HttpException

class UserRepositoryImpl(
): UserRepository{

    override suspend fun createClient(user: UserModel): Resource<Unit>{
        return try{
           // val response = SrcameloApiService.createUser(user)
            Resource.Success(Unit)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        } catch( e: IOException){
            Resource.Error("${e.message}")
        }
    }
}