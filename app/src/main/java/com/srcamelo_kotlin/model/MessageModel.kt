package com.srcamelo_kotlin.model

import com.google.gson.annotations.SerializedName

data class MessageModel(
    @SerializedName("_id")
    val oid: Id = Id(""),
    @SerializedName("participants")
    val participants: MutableList<String> = mutableListOf("", ""),
    @SerializedName("sender")
    val sender: String = "",
    @SerializedName("receiver")
    val receiver: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("datetime")
    val time: String = "",
    @SerializedName("participantDetails")
    val participantDetails: List<ParticipantDetail> = emptyList()
)

data class ParticipantDetail(
    @SerializedName("userId")
    val userId: String = "",
    @SerializedName("userName")
    val userName: String = "",
    @SerializedName("image")
    val image: String? = null
)
