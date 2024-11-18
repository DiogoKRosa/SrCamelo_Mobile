package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class ResponseProduct (
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
    @SerializedName("image")
    val image: String = ""
)
