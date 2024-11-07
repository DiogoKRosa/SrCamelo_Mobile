package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.model.User
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.data.repository.UserRepositoryImpl

data class ValidationResult(
    val passwordError: String? = null,
    val result: Resource<Unit>? = null
)

class CreateClientUseCase(
    private val repository: UserRepositoryImpl
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
    ):ValidationResult{

        if(password != passwordC){
            return ValidationResult(passwordError = "As senhas não coincidem")
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

        return ValidationResult( result = repository.createClient(request))
    }
}