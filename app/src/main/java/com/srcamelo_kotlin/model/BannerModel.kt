package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class BannerModel(
    @SerializedName("_id")
    val id: Id? = null,
    @SerializedName("fantasyName")
    val fantasyName: String? = null,
    @SerializedName("paymentMethods")
    val paymentMethods: List<String>? = null,
)