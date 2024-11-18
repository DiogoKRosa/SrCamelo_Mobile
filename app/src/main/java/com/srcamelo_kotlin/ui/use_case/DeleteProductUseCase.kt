package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.ProductRepositoryImpl
import com.srcamelo_kotlin.model.ResponseProduct
import com.srcamelo_kotlin.network.Resource
import javax.inject.Inject

data class DeleteProductResult(val products: Resource<Any>)

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepositoryImpl
) {
    suspend operator fun invoke(id: String): DeleteProductResult {
        return DeleteProductResult(repository.deleteProduct(id))
    }
}