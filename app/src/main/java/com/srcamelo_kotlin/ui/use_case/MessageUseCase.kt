package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.MessageRepositoryImpl
import com.srcamelo_kotlin.model.MessageModel
import com.srcamelo_kotlin.network.Resource
import javax.inject.Inject

data class MessageResult(
    val result: Resource<Any>? = null
)

class GetLastMessageUseCase @Inject constructor(
    private val repositoryImpl: MessageRepositoryImpl
){
    suspend operator fun invoke(
        userId: String
    ): MessageResult {

        return MessageResult(repositoryImpl.getAllLastMessages(userId))

    }
}

class GetPrivateChatUseCase @Inject constructor(
    private val repositoryImpl: MessageRepositoryImpl
){
    suspend operator fun invoke(
        loginId: String,
        userId: String
    ): MessageResult {

        return MessageResult(repositoryImpl.getPrivateChatContent(loginId, userId))
    }
}

class SendMessageUseCase @Inject constructor(
    private val repositoryImpl: MessageRepositoryImpl
){
    suspend operator fun invoke(
        message: MessageModel
    ): MessageResult{
        return MessageResult(repositoryImpl.sendMessage(message))
    }
}