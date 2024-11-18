package com.srcamelo_kotlin.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class ProductModel(
    @SerializedName("_id")
    val id: Id? = null,
    @SerializedName("vendor_id")
    val vendorId: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("category")
    val category: String = "",
)
