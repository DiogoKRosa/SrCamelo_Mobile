package com.srcamelo_kotlin.network

import com.srcamelo_kotlin.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "http://10.0.2.2:8000"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface SrcameloApiService{
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @POST("users")
    suspend fun createUser(
        @Body registerUserRequest: User
    ): Response<String>
}

object SrcameloApi{
    val retrofitService : SrcameloApiService by lazy {
        retrofit.create(SrcameloApiService::class.java)
    }
}
