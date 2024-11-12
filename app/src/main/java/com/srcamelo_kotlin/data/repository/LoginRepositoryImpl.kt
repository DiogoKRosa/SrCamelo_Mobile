package com.srcamelo_kotlin.data.repository

import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.domain.repository.LoginRepository
import com.srcamelo_kotlin.model.LoginRequest
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: SrcameloApiService,
    private val preferences: DataStoreManager
) : LoginRepository{
    override suspend fun login(loginRequest: LoginRequest): Resource<Any> {
        return try{
            val response = apiService.login(loginRequest)
            preferences.setAuthToken(response.data.accessToken)
            Resource.Success(Unit)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        } catch( e: IOException){
            Resource.Error("${e.message}")
        }
    }
}