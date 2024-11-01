package com.srcamelo_kotlin.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.network.SrcameloApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UsersUiState{
    data class Success(val names: String) : UsersUiState
    object Error : UsersUiState
    object Loading : UsersUiState
}

// Link: https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet?hl=pt-br#6
class UsersViewModel(): ViewModel(){
    var usersUiState: UsersUiState by mutableStateOf(UsersUiState.Loading)
    private set

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val listResult = SrcameloApi.SrcameloApiService.getUsers()
                usersUiState = UsersUiState.Success(listResult)
            } catch (e: IOException){
                usersUiState = UsersUiState.Error
            }
        }
    }
}