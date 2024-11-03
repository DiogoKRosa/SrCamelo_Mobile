package com.srcamelo_kotlin.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://10.0.2.2:8000"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface SrcameloApiService{
    @GET("users")
    suspend fun getUsers(): String
}

object SrcameloApi{
    val retrofitService : SrcameloApiService by lazy {
        retrofit.create(SrcameloApiService::class.java)
    }
}
