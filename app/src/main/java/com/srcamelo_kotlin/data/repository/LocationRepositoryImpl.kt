package com.srcamelo_kotlin.data.repository

import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.model.LocationModel
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApiService
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiService: SrcameloApiService,
    private val preferences: DataStoreManager
){
    suspend fun updateLocation(request: LocationModel): Resource<Any> {
        return try {
            val response = apiService.updateLocation(request)
            Resource.Success(response)
        } catch (e: HttpException) {
            Resource.Error("Erro HTTP: ${e.message}")
        } catch (e: IOException) {
            Resource.Error("Erro de IO: ${e.message}")
        } catch (e: SocketTimeoutException) {
            Resource.Error("Timeout: ${e.message}")
        } catch (e: Exception) {
            Resource.Error("Erro Desconhecido: ${e.message}")
        }
    }
}