package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.MessageRepositoryImpl
import com.srcamelo_kotlin.model.MessageModel
import com.srcamelo_kotlin.network.Resource
import javax.inject.Inject

data class MessageResult<T>(
    val result: Resource<T>? = null
)

class GetLastMessageUseCase @Inject constructor(
    private val repositoryImpl: MessageRepositoryImpl
){
    suspend operator fun invoke(
        userId: String
    ): MessageResult<List<MessageModel>> {

        return MessageResult(repositoryImpl.getAllLastMessages(userId) as Resource<List<MessageModel>>)

    }
}

class GetPrivateChatUseCase @Inject constructor(
    private val repositoryImpl: MessageRepositoryImpl
){
    suspend operator fun invoke(
        loginId: String,
        userId: String
    ): MessageResult<List<MessageModel>> {

        return MessageResult(repositoryImpl.getPrivateChatContent(loginId, userId) as Resource<List<MessageModel>>)
    }
}

class SendMessageUseCase @Inject constructor(
    private val repositoryImpl: MessageRepositoryImpl
){
    suspend operator fun invoke(
        message: MessageModel
    ): MessageResult<Any>{
        return MessageResult(repositoryImpl.sendMessage(message))
    }
}