package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.repository.CreateUserRepository

data class validationResult(
    val passwordError: String? = null,
    val result: Resource<Unit>? = null
)

class CreateClientUseCase(
    private val repository: CreateUserRepository
){
    suspend operator fun invoke(
        userType: String,
        name: String,
        cpf: String,
        email: String,
        telephone: String,
        password: String,
        passwordC: String,
        country: String,
        uf: String,
        city: String
    ):validationResult{

        if(password != passwordC){
            return validationResult(passwordError = "As senhas não coincidem")
        }

        val request = User(
            userType = userType,
            name = name,
            cpf = cpf,
            email = email,
            telephone = telephone,
            password = password,
            country = country,
            uf = uf,
            city = city
        )

        return validationResult( result = repository.createClient(request))
    }
}