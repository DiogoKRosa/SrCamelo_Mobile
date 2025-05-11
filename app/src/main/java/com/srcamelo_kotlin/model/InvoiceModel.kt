package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class InvoiceModel(
    @SerializedName("_id")
    val invoiceId: Id = Id(""),
    @SerializedName("invoiceStatus")
    val invoiceStatus: String = "",
    @SerializedName("clientId")
    val clientId: String = "",
    @SerializedName("clientName")
    val clientName: String = "",
    @SerializedName("clientNumber")
    val clientNumber: String = "",
    @SerializedName("vendorId")
    val vendorId: String = "",
    @SerializedName("vendorName")
    val vendorName: String = "",
    @SerializedName("vendorNumber")
    val vendorNumber: String = "",
    @SerializedName("invoiceTotal")
    val invoiceTotal: Double = 0.00,
    @SerializedName("productsList")
    val productsList: List<ProductInvoiceModel> = emptyList(),
    @SerializedName("datetime")
    val datetime: String = "",
    @SerializedName("paymentType")
    var paymentType: String = ""
)

data class ProductInvoiceModel(
    @SerializedName("productId")
    val productId: String = "",
    @SerializedName("productName")
    val productName: String = "",
    @SerializedName("productQtd")
    val productQtd: Int = 0,
    @SerializedName("productPrice")
    val productPrice: Double = 0.0
)
