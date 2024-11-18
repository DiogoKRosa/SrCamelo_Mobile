package com.srcamelo_kotlin.ui.viewModel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.model.ResponseProduct
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.ui.use_case.CreateProductUseCase
import com.srcamelo_kotlin.ui.use_case.DeleteProductUseCase
import com.srcamelo_kotlin.ui.use_case.GetProductUseCase
import com.srcamelo_kotlin.ui.use_case.UpdateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val createProductUseCase: CreateProductUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase

) : ViewModel(){

    private val _products = MutableLiveData<List<ResponseProduct>>()
    val products: LiveData<List<ResponseProduct>> = _products

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getProductsFromVendor(userId: String = "1"){
        viewModelScope.launch{

            val response = getProductUseCase(userId)

            when(response.products){
                is Resource.Success -> {
                    _products.value = response.products.data!!
                }
                is Resource.Error -> {
                    println("Error: ${response.products.message}")
                }
                else ->{

                }
            }
            _loading.value = false
        }
    }

    fun createProduct(userId: String = "",productName: String, productPrice: Double, productDescription: String, productCategory: String, productImage: Uri, context: Context){
        viewModelScope.launch{

            val response =  createProductUseCase(
                vendorId = userId,
                name = productName,
                price = productPrice,
                description = productDescription,
                category = productCategory,
                image = prepareFilePart(productImage, context)
            )
            println(createProductUseCase)

            when(response.result){
                is Resource.Success -> {
                    println("Success: ${response.result.data}")
                    getProductsFromVendor(userId)
                }
                is Resource.Error -> {
                    println("Error: ${response.result.message}")
                }
                else -> {

                }
            }
        }
    }

    private fun prepareFilePart(uri: Uri, context: Context): MultipartBody.Part {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri) ?: return MultipartBody.Part.createFormData("image", "")
        val file = File(context.cacheDir, "temp_image.jpg").apply {
            outputStream().use { output ->
                inputStream.copyTo(output)
            }
        }

        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }

    fun deleteProduct(productId: String, userId: String = "1"){
        viewModelScope.launch {
            val response = deleteProductUseCase(productId)
            when(response.products){
                is Resource.Success -> {
                    println("Success: ${response.products.message}")
                    getProductsFromVendor(userId)
                }
                is Resource.Error -> {
                    println("Error: ${response.products.message}")
                }
                else ->{

                }
            }
            _loading.value = false
        }
    }

    fun updateProduct(productId: String ,userId: String = "1", productName: String, productPrice: Double, productDescription: String, productCategory: String, productImage: Uri, context: Context){
        viewModelScope.launch{
            val response = updateProductUseCase(
                productId = productId,
                name = productName,
                price = productPrice,
                description = productDescription,
                category = productCategory,
                image = prepareFilePart(productImage, context)
            )

            when(response.result){
                is Resource.Success -> {
                    println("Success: ${response.result.data}")
                    getProductsFromVendor(userId)
                }
                is Resource.Error -> {
                    println("Error: ${response.result.message}")
                }
                else -> {

                }
            }
        }
    }
}
