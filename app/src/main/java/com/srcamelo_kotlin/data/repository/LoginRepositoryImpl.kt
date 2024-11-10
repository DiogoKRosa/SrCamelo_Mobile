package com.srcamelo_kotlin.data.repository

import androidx.datastore.preferences.preferencesDataStore
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.domain.repository.LoginRepository
import com.srcamelo_kotlin.model.LoginRequest
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApi
import retrofit2.HttpException
import java.io.IOException

class LoginRepositoryImpl(
) : LoginRepository{
    override suspend fun login(loginRequest: LoginRequest): Resource<Unit> {
        return try{
            val response = SrcameloApi.retrofitService.login(loginRequest)
            //preferences.setAuthToken(response.data.accessToken)
            Resource.Success(Unit)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        } catch( e: IOException){
            Resource.Error("${e.message}")
        }
    }
}