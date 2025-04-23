package com.srcamelo_kotlin.network

import com.srcamelo_kotlin.model.BannerModel
import com.srcamelo_kotlin.model.LocationModel
import com.srcamelo_kotlin.model.LoginRequest
import com.srcamelo_kotlin.model.ReponseUser
import com.srcamelo_kotlin.model.ResponseModel
import com.srcamelo_kotlin.model.ResponseProduct
import com.srcamelo_kotlin.model.ResponseToken
import com.srcamelo_kotlin.model.UserModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path


interface SrcameloApiService{
    @GET("users")
    suspend fun getUsers(): Response<List<UserModel>>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") userId:String
    ): UserModel

    @POST("users")
    suspend fun createUser(
        @Body registerUserRequest: UserModel
    ): ReponseUser

    @Multipart
    @PUT("newVendor/{vendor_id}")
    suspend fun updateBannerVendor(
        @Path("vendor_id") userId: String,
        @Part("bannerFormVendor") bannerFormVendor: BannerModel,
        @Part image: MultipartBody.Part? = null
    )

    @POST("login")
    suspend fun login(
        @Body loginUserRequest: LoginRequest
    ): ResponseToken


    @Multipart
    @POST("products")
    suspend fun createProduct(
        @Part("product") product: RequestBody,
        @Part image: MultipartBody.Part? = null
    ): ResponseModel

    @GET("products/{vendor_id}")
    suspend fun getProductsById(
        @Path("vendor_id") userId:String
    ): List<ResponseProduct>

    @DELETE("products/{product_id}")
    suspend fun deleteProduct(
        @Path("product_id") productId:String
    ): ResponseModel

    @Multipart
    @PUT("products/{product_id}")
    suspend fun updateProduct(
        @Part("product") product: RequestBody,
        @Part image: MultipartBody.Part? = null
    ): ResponseModel

    @POST("location")
    suspend fun updateLocation(
        @Body data: LocationModel,
    ): ResponseModel

    @GET("location")
    suspend fun getAllLocation(): ResponseModel

    @GET("location/{user_id}")
    suspend fun getUserLocation(
        @Path("user_id") userId: String
    ): ResponseModel
}
