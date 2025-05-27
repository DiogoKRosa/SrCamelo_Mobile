package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String = "",
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
)
