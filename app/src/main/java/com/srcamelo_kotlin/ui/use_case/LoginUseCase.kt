package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.LoginRepositoryImpl
import com.srcamelo_kotlin.model.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repositoryImpl: LoginRepositoryImpl
){
    //private val repository = LoginRepositoryImpl()

    suspend operator fun invoke(
        email: String,
        password: String
    ): ValidationResult {
        val request = LoginRequest(
            email = email,
            password = password
        )

        return ValidationResult( result = repositoryImpl.login(request))
    }
}