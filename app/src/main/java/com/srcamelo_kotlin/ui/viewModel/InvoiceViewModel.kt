package com.srcamelo_kotlin.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.model.InvoiceModel
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.ui.use_case.SendInvoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val sendInvoiceUseCase: SendInvoiceUseCase
): ViewModel() {
    var invoiceToSend: InvoiceModel? = null

    fun sendInvoice(invoice: InvoiceModel){
        viewModelScope.launch {
            try{
                val res = sendInvoiceUseCase(invoice)
                when(res.result){
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        Log.d("InvoiceViewModel", "SendInvoice: ${res.result?.data}")
                    }
                    else -> {}
                }
            }catch (e: Exception){

            }
        }
    }
}