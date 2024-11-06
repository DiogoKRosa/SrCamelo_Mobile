package com.srcamelo_kotlin.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontLoadingStrategy.Companion.Async
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.network.SrcameloApi
import com.srcamelo_kotlin.network.SrcameloApiService
import com.srcamelo_kotlin.ui.components.BackTopAppBar
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.components.SpecialText
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.viewModel.UsersUiState
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.Async
import kotlin.math.log

@Composable
fun NewFormClientScreen(
    onClickBack : () -> Unit = {},
    onSubmit: (User)-> Unit = {},
    onClickLogin: () -> Unit = {}
){
    Scaffold (topBar = {BackTopAppBar(onClickBack = onClickBack)},
        content = {
            val scrollState = rememberScrollState()

            var nameUser by remember { mutableStateOf("")}

            var userModel : UsersViewModel = viewModel()
            Column(
                modifier = Modifier
                    .background(color = LightOrange)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text("Cadastro", fontFamily = Montserrat, fontWeight = FontWeight.Medium, fontSize = 24.sp, color = DarkOrange)
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(painter = painterResource(id = R.drawable.login_icon), contentDescription = "")
                }
                Spacer(modifier = Modifier.height(33.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {


                    InputLine(placeholder = "Nome", value = nameUser, onValueChange = { it -> nameUser = it})

                    InputLine(placeholder = "CPF")


                    InputLine(placeholder = "E-mail")


                    InputLine(placeholder = "Telefone")


                    InputLine(placeholder = "Senha")


                    InputLine(placeholder = "Confirmar Senha")
                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)){


                        InputLine(placeholder = "País", modifier = Modifier.width(169.dp))


                        InputLine(placeholder = "UF", modifier = Modifier.width(79.dp))
                    }


                    InputLine(placeholder = "Cidade")

                }
                Spacer(modifier = Modifier.height(100.dp))
                ButtonWhite(title="Cadastrar-se", onClick = {
                    val user = User(
                        userType = "",
                        name = nameUser,
                        city= "",
                        country = "",
                        uf = "",
                        cpf = "",
                        email = "",
                        password = "",
                        telephone = "")
//                    runBlocking {
//                        launch{
//                            SrcameloApi.retrofitService.createUser(user)
//                        }
//                    }
//                    var uiState = userModel.usersUiState
//                    userModel.createUser(user)
//                    when(uiState){
//                        is UsersUiState.Loading -> Log.e("Load", "Cadastrando...")
//                        is UsersUiState.Error -> Log.e("Erro", "Houve um problema ao Cadastrar")
//                        is UsersUiState.Success -> Log.e("Sucesso", uiState.names)
//                    }
                })
                SpecialText(text = "Já possuo uma conta", onClick = onClickLogin)
            }
        })
}