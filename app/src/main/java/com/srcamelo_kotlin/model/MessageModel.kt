package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class MessageModel(
    @SerializedName("_id")
    val oid: Id,
    @SerializedName("participants")
    val participants: MutableList<String>,
    @SerializedName("sender")
    val sender: String,
    @SerializedName("receiver")
    val receiver: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("datetime")
    val time: String
)
