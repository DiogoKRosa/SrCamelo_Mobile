package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.ProductRepositoryImpl
import com.srcamelo_kotlin.model.ProductModel
import com.srcamelo_kotlin.network.Resource
import okhttp3.MultipartBody
import javax.inject.Inject

data class ProductResult(
    val result: Resource<Any>? = null
)

class CreateProductUseCase @Inject constructor(
    private val repository: ProductRepositoryImpl
) {
    suspend operator fun invoke(
        vendorId: String,
        name: String,
        price: Double,
        description: String,
        category: String,
        image: MultipartBody.Part? = null
    ): ProductResult {

        val product = ProductModel(
            vendorId = vendorId,
            name = name,
            price = price,
            description = description,
            category = category,
        )

        return ProductResult(repository.createProduct(product, image))
    }
}