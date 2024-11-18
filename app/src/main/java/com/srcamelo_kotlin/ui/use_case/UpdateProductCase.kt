package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.ProductRepositoryImpl
import com.srcamelo_kotlin.model.Id
import com.srcamelo_kotlin.model.ProductModel
import com.srcamelo_kotlin.network.Resource
import okhttp3.MultipartBody
import javax.inject.Inject

data class UpdateProductResult(val result: Resource<Any>)

class UpdateProductUseCase @Inject constructor(
    private val repository: ProductRepositoryImpl
) {
    suspend operator fun invoke(
        productId: String,
        name: String,
        price: Double,
        description: String,
        category: String,
        image: MultipartBody.Part? = null,
    ): UpdateProductResult {
        val product = ProductModel(
            id = Id(productId),
            name = name,
            price = price,
            description = description,
            category = category
        )
        return UpdateProductResult(repository.updateProduct(product, image))
    }
}