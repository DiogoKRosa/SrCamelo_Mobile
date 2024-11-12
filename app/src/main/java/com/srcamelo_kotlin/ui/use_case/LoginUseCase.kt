package com.srcamelo_kotlin.ui.use_case

import androidx.datastore.dataStore
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.data.repository.LoginRepositoryImpl
import com.srcamelo_kotlin.model.LoginRequest
import com.srcamelo_kotlin.network.Resource
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