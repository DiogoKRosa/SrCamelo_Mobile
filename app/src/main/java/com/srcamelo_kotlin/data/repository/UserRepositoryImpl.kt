package com.srcamelo_kotlin.data.repository

import android.media.Image
import android.util.Log
import com.srcamelo_kotlin.domain.repository.UserRepository
import com.srcamelo_kotlin.model.BannerModel
import com.srcamelo_kotlin.model.Id
import com.srcamelo_kotlin.model.UserModel
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApiService
import okhttp3.MultipartBody
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: SrcameloApiService
){

    suspend fun createClient(user: UserModel): Resource<Any>{
        return try{
            val response = api.createUser(user)
            Resource.Success(response)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        } catch( e: IOException){
            Resource.Error("${e.message}")
        }
    }

    suspend fun updateBannerVendor(userId: String, bannerModel: BannerModel, bannerImgVendor: MultipartBody.Part? = null): Resource<Any>{
        return try{
                val response = api.updateBannerVendor(userId, bannerModel, bannerImgVendor)
                return Resource.Success(response)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        } catch( e: IOException){
            Resource.Error("${e.message}")
        }
    }

    suspend fun getUserById(id: String): Resource<UserModel>{
        println("getUserById called with id: $id")
        return try{
            val response = api.getUserById(id)
            print(response)
            Resource.Success(response)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        } catch( e: IOException){
            Resource.Error("${e.message}")
        }
    }
}