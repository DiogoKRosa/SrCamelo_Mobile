package com.srcamelo_kotlin.data.repository

import com.google.gson.Gson
import com.srcamelo_kotlin.model.ProductModel
import com.srcamelo_kotlin.model.ResponseProduct
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.network.SrcameloApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val serviceApi: SrcameloApiService,
){
    suspend fun createProduct(product: ProductModel, image: MultipartBody.Part?): Resource<Any> {
        return try{
            val productJson = Gson().toJson(product)
            val productRequestBody = productJson.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            val response = serviceApi.createProduct(productRequestBody, image)
            return Resource.Success(response)
        } catch( e: HttpException){
            Resource.Error("${e.message}")
        } catch( e: IOException){
            Resource.Error("${e.message}")
        }
    }

    suspend fun getProductsByVendor(userId: String): Resource<List<ResponseProduct>>{
        return try{
            val response = serviceApi.getProductsById(userId)
            return Resource.Success(response)
        }catch( e : HttpException){
            Resource.Error("${e.message}")
        }catch( e : IOException){
            Resource.Error("${e.message}")
        }
    }

    suspend fun deleteProduct(productId: String): Resource<Any>{
        return try{
            val response = serviceApi.deleteProduct(productId)
            return Resource.Success(response)
        }catch( e : HttpException){
            Resource.Error("${e.message}")
        }catch( e : IOException){
            Resource.Error("${e.message}")
        }
    }

    suspend fun updateProduct(product: ProductModel, image: MultipartBody.Part?): Resource<Any>{
        return try{
            val productJson = Gson().toJson(product)
            val productRequestBody = productJson.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            val response = serviceApi.updateProduct(productRequestBody, image)
            return Resource.Success(response)
        }catch( e : HttpException){
            Resource.Error("${e.message}")
        }catch( e : IOException){
            Resource.Error("${e.message}")
        }
    }
}