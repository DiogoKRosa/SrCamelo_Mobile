package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.InvoiceRepositoryImpl
import com.srcamelo_kotlin.model.InvoiceModel
import com.srcamelo_kotlin.network.Resource
import javax.inject.Inject

data class InvoiceResult<T>(
    val result: Resource<T>? = null
)

class SendInvoiceUseCase @Inject constructor(
    private val repositoryImpl: InvoiceRepositoryImpl
){
    suspend operator fun invoke(
        invoice: InvoiceModel
    ): InvoiceResult<Any>{
        return InvoiceResult(repositoryImpl.SendInvoice(invoice))
    }
}