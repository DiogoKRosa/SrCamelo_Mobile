package com.srcamelo_kotlin.ui.use_case

import android.health.connect.changelog.ChangeLogTokenResponse
import com.srcamelo_kotlin.data.repository.LoginRepositoryImpl
import com.srcamelo_kotlin.model.LoginRequest
import com.srcamelo_kotlin.model.ResponseToken
import com.srcamelo_kotlin.model.TokenModel
import com.srcamelo_kotlin.network.Resource
import javax.inject.Inject

data class LoginResult(
    val result: Resource<TokenModel>? = null,
)

class LoginUseCase @Inject constructor(
    private val repositoryImpl: LoginRepositoryImpl
){
    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResult {
        val request = LoginRequest(
            email = email,
            password = password
        )

        return LoginResult( result = repositoryImpl.login(request))
    }
}