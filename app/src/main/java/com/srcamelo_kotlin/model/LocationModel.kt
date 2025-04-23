package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
