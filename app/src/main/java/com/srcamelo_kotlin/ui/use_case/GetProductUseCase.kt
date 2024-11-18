package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.ProductRepositoryImpl
import com.srcamelo_kotlin.model.ResponseProduct
import com.srcamelo_kotlin.network.Resource
import retrofit2.Response
import javax.inject.Inject

data class GetProductResult(val products: Resource<List<ResponseProduct>>)

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepositoryImpl
) {
    suspend operator fun invoke(id: String): GetProductResult {
        return GetProductResult(repository.getProductsByVendor(id))
    }
}