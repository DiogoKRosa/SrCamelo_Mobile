package com.srcamelo_kotlin.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.model.InvoiceModel
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.ui.use_case.GetInvoiceUseCase
import com.srcamelo_kotlin.ui.use_case.SendInvoiceUseCase
import com.srcamelo_kotlin.ui.use_case.UpdateInvoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val sendInvoiceUseCase: SendInvoiceUseCase,
    private val getInvoiceUseCase: GetInvoiceUseCase,
    private val updateInvoiceUseCase: UpdateInvoiceUseCase
): ViewModel() {
    var invoiceToSend: InvoiceModel? = null

    private val _invoiceList = MutableLiveData<List<InvoiceModel>>()
    val invoiceList: LiveData<List<InvoiceModel>> = _invoiceList

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

    fun getInvoices(userId: String){
        viewModelScope.launch{
            try{
                val res = getInvoiceUseCase(userId)
                when(res.result){
                    is Resource.Error -> {
                        res.result.message?.let { Log.d("getInvoiceClient", it) }
                    }
                    is Resource.Success -> {
                        val invoices = res.result.data as? List<InvoiceModel> ?: emptyList()
                        _invoiceList.value = invoices
                    }
                    else -> {
                        res.result?.message?.let { Log.d("getInvoiceClient", it) }
                    }
                }
            }catch(e: Exception){

            }
        }
    }

    fun updateInvoice(invoiceId: String, status: String){
        viewModelScope.launch{
            try{
                val res = updateInvoiceUseCase(invoiceId, status)
            }catch(e: Exception){

            }
        }
    }
}