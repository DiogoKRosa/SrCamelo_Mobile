package com.srcamelo_kotlin.ui.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.common.TextFieldState
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.ui.use_case.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
): ViewModel() {
    private val loginUseCase = LoginUseCase()

    private var usersUiState = mutableStateOf(UserState())
    val uiState: State<UserState> = usersUiState

    private val _email = mutableStateOf(TextFieldState())
    val email: State<TextFieldState> = _email
    fun setEmail(value: String) {
        _email.value = email.value.copy(text = value)
    }

    private val _password = mutableStateOf(TextFieldState())
    val password: State<TextFieldState> = _password
    fun setPassword(value: String) {
        _password.value = password.value.copy(text = value)
    }

    fun login() {
        viewModelScope.launch {
            usersUiState.value = uiState.value.copy(status = false)

            Log.e("POST", "${email.value.text} ${password.value.text}")

            val loginRequest = loginUseCase(
                email = email.value.text,
                password = password.value.text
            )

            when (loginRequest.result) {
                is Resource.Success -> {
                    Log.e("POST", "Usuário encontrado")
                    usersUiState.value = uiState.value.copy(status = true)

                }

                is Resource.Error -> {
                    Log.e("ERRO", "${loginRequest.result.message}")
                    usersUiState.value =
                        uiState.value.copy(status = false, isError = loginRequest.result.message)
                }

                else -> {

                }
            }
        }
    }
}