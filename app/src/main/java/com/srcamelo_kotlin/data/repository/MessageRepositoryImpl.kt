package com.srcamelo_kotlin.data.repository

import com.srcamelo_kotlin.model.MessageModel
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApiService
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val api : SrcameloApiService
) {
    suspend fun getAllLastMessages(userId: String): Resource<Any> {
        return try {

            val response = api.getAllUniqueLastMessages(userId)
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

    suspend fun getPrivateChatContent(loginId: String, userId: String): Resource<Any>{
        return try{
            val response = api.getAllMessagesBetweenUsers(loginId, userId)
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

    suspend fun sendMessage(message: MessageModel): Resource<Any>{
        return try{
            val response = api.sendMessage(message)
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