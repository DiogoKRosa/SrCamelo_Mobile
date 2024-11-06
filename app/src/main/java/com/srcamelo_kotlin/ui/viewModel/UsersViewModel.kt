package com.srcamelo_kotlin.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.network.SrcameloApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UsersUiState{
    data class Success(val response: String) : UsersUiState
    object Error : UsersUiState
    object Loading : UsersUiState
}

// Link: https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet?hl=pt-br#6
class UsersViewModel(): ViewModel(){
    var usersUiState: UsersUiState by mutableStateOf(UsersUiState.Loading)
        private set

    fun getUsers() {
        viewModelScope.launch {
            try {
                val listResult = SrcameloApi.retrofitService.getUsers()
                usersUiState = UsersUiState.Success(listResult)
            } catch (e: IOException){
                Log.e("CourontineError", "Coroutine encountered an error", e)
                usersUiState = UsersUiState.Error
            }
        }
    }

    fun createUser(user: User){
        viewModelScope.launch {
            try {
                SrcameloApi.retrofitService.createUser(user)
                usersUiState = UsersUiState.Success("Usuário Cadastrado com sucesso")
            } catch (e: IOException){
                Log.e("CourontineError", "Coroutine encountered an error", e)
                usersUiState = UsersUiState.Error
            }
        }
    }
}