package com.srcamelo_kotlin.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.ui.components.BackTopAppBar
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.components.SpecialText
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel

@Composable
fun NewFormClientScreen(
    onClickBack : () -> Unit = {},
    onSubmit: (User)-> Unit = {},
    onClickLogin: () -> Unit = {},
    viewModel: UsersViewModel = viewModel()
){
    Scaffold (topBar = {BackTopAppBar(onClickBack = onClickBack)},
        content = {
            val scrollState = rememberScrollState()

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

                    val nameState = viewModel.name.value
                    InputLine(placeholder = "Nome", value = nameState.text, onValueChange = { it -> viewModel.setName(it)})

                    val cpfState = viewModel.cpf.value
                    InputLine(placeholder = "CPF", value = cpfState.text, onValueChange = {it -> viewModel.setCpf(it)})

                    val emailState = viewModel.email.value
                    InputLine(placeholder = "E-mail", value = emailState.text, onValueChange = {it -> viewModel.setEmail(it)})

                    val telephoneState = viewModel.telephone.value
                    InputLine(placeholder = "Telefone", value = telephoneState.text, onValueChange = {it -> viewModel.setTelephone(it)})

                    val passwordState = viewModel.password.value
                    InputLine(placeholder = "Senha", value = passwordState.text, onValueChange = {it -> viewModel.setPassword(it)})

                    val passwordCState = viewModel.passwordC.value
                    InputLine(placeholder = "Confirmar Senha", value = passwordCState.text, onValueChange = {it -> viewModel.setPasswordC(it)})

                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)){

                        val countryState = viewModel.country.value
                        InputLine(placeholder = "País", modifier = Modifier.width(169.dp), value = countryState.text, onValueChange = {it -> viewModel.setCountry(it)})

                        val ufState = viewModel.uf.value
                        InputLine(placeholder = "UF", modifier = Modifier.width(79.dp), value = ufState.text, onValueChange = {it -> viewModel.setUf(it)})
                    }

                    val cityState = viewModel.city.value
                    InputLine(placeholder = "Cidade", value = cityState.text, onValueChange = {it -> viewModel.setCity(it)})

                }
                Spacer(modifier = Modifier.height(100.dp))
                ButtonWhite(title="Cadastrar-se", onClick = {
//                    val user = User(
//                        userType = "",
//                        name = nameUser,
//                        city= "",
//                        country = "",
//                        uf = "",
//                        cpf = "",
//                        email = "",
//                        password = "",
//                        telephone = "")
//
//                    var uiState = userModel.usersUiState
//                    userModel.createUser(user)
//                    when(uiState){
//                        is UsersUiState.Loading -> Log.e("Load", "Cadastrando...")
//                        is UsersUiState.Error -> Log.e("Erro", "Houve um problema ao Cadastrar")
//                        is UsersUiState.Success -> Log.e("Sucesso", uiState.response)
//                    }
                    viewModel.createUser()
                })
                SpecialText(text = "Já possuo uma conta", onClick = onClickLogin)
            }
        })
}