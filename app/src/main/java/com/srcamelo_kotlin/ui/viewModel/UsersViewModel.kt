package com.srcamelo_kotlin.ui.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.common.TextFieldState
import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.network.SrcameloApi
import com.srcamelo_kotlin.ui.use_case.CreateClientUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface UsersUiState {
    data class Success(val response: String) : UsersUiState
    object Error : UsersUiState
    object Loading : UsersUiState
}

// Link: https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet?hl=pt-br#6
class UsersViewModel @Inject constructor(
    private val createClientUseCase: CreateClientUseCase
) : ViewModel() {
    var usersUiState: UsersUiState by mutableStateOf(UsersUiState.Loading)
        private set

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


//    fun getUsers() {
//        viewModelScope.launch {
//            try {
//                val listResult = SrcameloApi.retrofitService.getUsers()
//                usersUiState = UsersUiState.Success(listResult)
//            } catch (e: IOException){
//                Log.e("CourontineError", "Coroutine encountered an error", e)
//                usersUiState = UsersUiState.Error
//            }
//        }
//    }

    fun createClient() {
        viewModelScope.launch {
            val createClientRequest = CreateClientUseCase(
                userType = "Cliente",
                name = name.value.text,
                city = city.value.text,
                country = country.value.text,
                uf = uf.value.text,
                cpf = cpf.value.text,
                email = email.value.text,
                password = password.value.text,
                telephone = telephone.value.text
            )

        }
    }
}