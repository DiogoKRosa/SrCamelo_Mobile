package com.srcamelo_kotlin.network

import com.srcamelo_kotlin.model.LoginRequest
import com.srcamelo_kotlin.model.ReponseUser
import com.srcamelo_kotlin.model.ResponseModel
import com.srcamelo_kotlin.model.ResponseToken
import com.srcamelo_kotlin.model.UserModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//private const val BASE_URL = "http://10.0.2.2:8000"
//
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create())
//    .baseUrl(BASE_URL)
//    .build()

interface SrcameloApiService{
    @GET("users")
    suspend fun getUsers(): Response<List<UserModel>>

    @POST("users")
    suspend fun createUser(
        @Body registerUserRequest: UserModel
    ): ReponseUser

    @POST("login")
    suspend fun login(
        @Body loginUserRequest: LoginRequest
    ): ResponseToken
}

//object SrcameloApi{
//    val retrofitService : SrcameloApiService by lazy {
//        retrofit.create(SrcameloApiService::class.java)
//    }
//}
