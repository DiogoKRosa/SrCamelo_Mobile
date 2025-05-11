package com.srcamelo_kotlin.ui.viewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srcamelo_kotlin.common.TextFieldState
import com.srcamelo_kotlin.model.UserModel
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.ui.use_case.CreateClientUseCase
import com.srcamelo_kotlin.ui.use_case.GetAllVendorsUseCase
import com.srcamelo_kotlin.ui.use_case.GetUserUseCase
import com.srcamelo_kotlin.ui.use_case.UpdateVendorBannerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

data class UserState(
    val status: Boolean = false,
    val isError: String? = null,
)

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val createClientUseCase: CreateClientUseCase,
    private val updateVendorBannerUseCase: UpdateVendorBannerUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getAllVendorsUseCase: GetAllVendorsUseCase
) : ViewModel() {

    private val _vendorList = MutableLiveData<List<UserModel>>()
    val vendorList: LiveData<List<UserModel>> = _vendorList

    private val _clientUser = MutableLiveData<UserModel?>()
    val clientUser: LiveData<UserModel?> = _clientUser

    private val _vendorUser = MutableLiveData<UserModel?>()
    val vendorUser: LiveData<UserModel?> = _vendorUser

    private val _userObj = MutableLiveData<UserModel?>()
    val userObj: MutableLiveData<UserModel?> = _userObj

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
                userType = "cliente",
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
                Log.e("Input error", "As senhas não coincidem")
                usersUiState.value = uiState.value.copy(status = false, isError = createClientRequest.passwordError)
            }
            when(createClientRequest.result){
                is Resource.Success -> {
                    Log.e("POST", "Usuário cadastrado")
                    Log.e("POST", "${createClientRequest.result.data}")
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

    fun createVendor(){
        viewModelScope.launch {
            usersUiState.value = uiState.value.copy(status = false)

            val createClientRequest = createClientUseCase(
                userType = "vendedor",
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
                Log.e("Input error", "As senhas não coincidem")
                usersUiState.value = uiState.value.copy(status = false, isError = createClientRequest.passwordError)
            }
            when(createClientRequest.result){
                is Resource.Success -> {
                    Log.e("POST", "Usuário cadastrado")
                    Log.e("POST", "${createClientRequest.result.data}")
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

    fun updateVendorBanner(
        userId: String,
        bannerUrl: Uri,
        fantasyName: String,
        paymentMethods: Map<String, Boolean>,
        context: Context
    ){
        viewModelScope.launch {
            val list: MutableList<String> = mutableListOf()
            paymentMethods.forEach(
                { key, value -> if(value){list.add(key)} }
            )
            val response = updateVendorBannerUseCase(
                userId = userId,
                bannerUrl = prepareFilePart(bannerUrl, context),
                fantasyName = fantasyName,
                paymentMethods = list
            )

            when(response.result){
                is Resource.Success -> {
                    println("Banner atualizado")
                }
                is Resource.Error -> {
                    print("Erro: ${response.result.message}")
                }
                else -> {

                }
            }
        }
    }

    fun getUserById(userId: String){
        viewModelScope.launch{
            val response = getUserUseCase(userId)
            when(response.result){
                is Resource.Success -> {
                    _userObj.value = response.result.data
                }
                is Resource.Error -> {
                    print("Erro: ${response.result.message}")
                }
                else -> {
                    Log.e("ERRO", "Erro Desconhecido")
                }
            }
        }
    }

    fun getVendorById(userId: String){
        viewModelScope.launch{
            val response = getUserUseCase(userId)
            when(response.result){
                is Resource.Success -> {
                    _vendorUser.value = response.result.data
                }
                is Resource.Error -> {
                    print("Erro: ${response.result.message}")
                }
                else -> {
                    Log.e("ERRO", "Erro Desconhecido")
                }
            }
        }
    }

    fun getClientById(userId: String){
        viewModelScope.launch{
            val response = getUserUseCase(userId)
            when(response.result){
                is Resource.Success -> {
                    _clientUser.value = response.result.data
                }
                is Resource.Error -> {
                    print("Erro: ${response.result.message}")
                }
                else -> {
                    Log.e("ERRO", "Erro Desconhecido")
                }
            }
        }
    }

    fun getVendors(){
        viewModelScope.launch{
            val response = getAllVendorsUseCase()
            when(response.result){
                is Resource.Success -> {
                    _vendorList.value = response.result.data as? List<UserModel> ?: emptyList()
                    Log.e("Debug", "GetAllVendors -> ${response.result.data}")
                }
                is Resource.Error -> {
                    Log.e("ERRO", "GetAllVendors -> ${response.result.message}")
                }
                else -> {
                    Log.e("ERRO", "GetAllVendors -> Desconhecido")
                }
            }
        }
    }

    private fun prepareFilePart(uri: Uri, context: Context): MultipartBody.Part {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri) ?: return MultipartBody.Part.createFormData("image", "")
        val file = File(context.cacheDir, "temp_image.jpg").apply {
            outputStream().use { output ->
                inputStream.copyTo(output)
            }
        }

        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }
}