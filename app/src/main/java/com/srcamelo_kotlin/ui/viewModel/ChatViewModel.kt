package com.srcamelo_kotlin.ui.viewModel


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.common.TextFieldState
import com.srcamelo_kotlin.model.MessageModel
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.ui.use_case.GetLastMessageUseCase
import com.srcamelo_kotlin.ui.use_case.GetPrivateChatUseCase
import com.srcamelo_kotlin.ui.use_case.MessageResult
import com.srcamelo_kotlin.ui.use_case.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState{
    object Loading: UiState()
    object Success: UiState()
    data class Error(val message: String): UiState()
}

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getLastMessageUseCase: GetLastMessageUseCase,
    private val getPrivateChatUseCase: GetPrivateChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase
):ViewModel(){
    private val _chatUiState = MutableLiveData<UiState>()
    val chatUiState: LiveData<UiState> = _chatUiState

    private val _chatList = MutableLiveData<List<MessageModel>>()
    val chatList: LiveData<List<MessageModel>> = _chatList

    private val _privateChatMessages = MutableLiveData<List<MessageModel>>()
    val privateChatMessages: LiveData<List<MessageModel>> = _privateChatMessages

    private val _message = mutableStateOf(TextFieldState())
    val message: State<TextFieldState> = _message
    fun setMessage(value: String){
        _message.value = message.value.copy(text = value)
    }

    fun GetAllLastMessage(
        loginId: String
    ){
        _chatUiState.value = UiState.Loading

        viewModelScope.launch{
            try{
                val response = getLastMessageUseCase(loginId)
                when(response.result){
                    is Resource.Error -> _chatUiState.value = UiState.Error("${response.result.message}")
                    is Resource.Success -> {
                        val messages = response.result.data as? List<MessageModel> ?: emptyList()
                        _chatList.value = messages
                        _chatUiState.value = UiState.Success
                    }
                    else -> _chatUiState.value = UiState.Error("Erro desconhecido")
                }
            } catch(e: Exception){
                _chatUiState.value = UiState.Error("Erro ao carregar mensagens: ${e}")
            }
        }
    }

    fun GetAllMessagesFromPrivateChat(
        loginId:String,
        userId: String
    ){
        viewModelScope.launch {
            try{
                val response = getPrivateChatUseCase(loginId = loginId, userId = userId)

                when(response.result){
                    is Resource.Error -> _chatUiState.value = UiState.Error("${response.result.message}")
                    is Resource.Success -> {
                        val messages = response.result.data as? List<MessageModel> ?: emptyList()
                        _privateChatMessages.value = messages
                        _chatUiState.value = UiState.Success
                    }
                    else -> _chatUiState.value = UiState.Error("Erro desconhecido")
                }
            }  catch(e: Exception){
                _chatUiState.value = UiState.Error("Erro ao carregar as mensagens")
            }
        }
    }

    fun SendMessage(
        message: MessageModel
    ){
        viewModelScope.launch{
            try{
                //Log.d("SEND_MESSAGE", message.toString())
                val response = sendMessageUseCase(message)

                when(response.result){
                    is Resource.Error -> _chatUiState.value = UiState.Error("${response.result.message}")
                    is Resource.Success -> {
                        setMessage("")
                        _chatUiState.value = UiState.Success
                    }
                    else -> _chatUiState.value = UiState.Error("Erro desconhecido")
                }

            } catch(e: Exception){
                _chatUiState.value = UiState.Error("Erro ao enviar a mensagem")
            }
        }
    }
}