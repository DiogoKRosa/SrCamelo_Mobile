package com.srcamelo_kotlin.ui.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.common.TextFieldState
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.ui.use_case.CreateClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserState(
    val status: Boolean = false,
    val isError: String? = null
)

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val createClientUseCase: CreateClientUseCase
) : ViewModel() {
    private var usersUiState = mutableStateOf(UserState())
    val uiState: State<UserState> = usersUiState

    private val _name = mutableStateOf(TextFieldState())
    val name: State<TextFieldState> = _name
    fun setName(value: String) {
        _name.value = name.value.copy(text = value)
    }

    private val _cpf = mutableStateOf(TextFieldState())
    val cpf: State<TextFieldState> = _cpf
    fun setCpf(value: String) {
        _cpf.value = cpf.value.copy(text = value)
    }

    private val _email = mutableStateOf(TextFieldState())
    val email: State<TextFieldState> = _email
    fun setEmail(value: String) {
        _email.value = email.value.copy(text = value)
    }

    private val _telephone = mutableStateOf(TextFieldState())
    val telephone: State<TextFieldState> = _telephone
    fun setTelephone(value: String) {
        _telephone.value = telephone.value.copy(text = value)
    }

    private val _password = mutableStateOf(TextFieldState())
    val password: State<TextFieldState> = _password
    fun setPassword(value: String) {
        _password.value = password.value.copy(text = value)
    }

    private val _passwordC = mutableStateOf(TextFieldState())
    val passwordC: State<TextFieldState> = _passwordC
    fun setPasswordC(value: String) {
        _passwordC.value = passwordC.value.copy(text = value)
    }

    private val _country = mutableStateOf(TextFieldState())
    val country: State<TextFieldState> = _country
    fun setCountry(value: String) {
        _country.value = country.value.copy(text = value)
    }

    private val _uf = mutableStateOf(TextFieldState())
    val uf: State<TextFieldState> = _uf
    fun setUf(value: String) {
        _uf.value = uf.value.copy(text = value)
    }

    private val _city = mutableStateOf(TextFieldState())
    val city: State<TextFieldState> = _city
    fun setCity(value: String) {
        _city.value = city.value.copy(text = value)
    }

    fun createClient(){
        viewModelScope.launch {
            usersUiState.value = uiState.value.copy(status = false)

            val createClientRequest = createClientUseCase(
                userType = "Cliente",
                name = name.value.text,
                city = city.value.text,
                country = country.value.text,
                uf = uf.value.text,
                cpf = cpf.value.text,
                email = email.value.text,
                password = password.value.text,
                passwordC = passwordC.value.text,
                telephone = telephone.value.text
            )

            if(createClientRequest.passwordError != null){
                usersUiState.value = uiState.value.copy(status = false, isError = createClientRequest.passwordError)
            }
            when(createClientRequest.result){
                is Resource.Success -> {
                    Log.e("POST", "Usuário cadastrado")
                    usersUiState.value = uiState.value.copy(status = true)
                }
                is Resource.Error -> {
                    Log.e("ERRO", "${createClientRequest.result.message}")
                    usersUiState.value = uiState.value.copy(status = false, isError = createClientRequest.result.message)
                }
                else -> {

                }
            }
        }
    }
}