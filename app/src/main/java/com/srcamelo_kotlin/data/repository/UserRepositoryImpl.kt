package com.srcamelo_kotlin.data.repository

import com.srcamelo_kotlin.domain.repository.UserRepository
import com.srcamelo_kotlin.model.UserModel
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApiService
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: SrcameloApiService
): UserRepository{

    override suspend fun createClient(user: UserModel): Resource<Any>{
        return try{
            val response = api.createUser(user)
            Resource.Success(response)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        } catch( e: IOException){
            Resource.Error("${e.message}")
        }
    }
}