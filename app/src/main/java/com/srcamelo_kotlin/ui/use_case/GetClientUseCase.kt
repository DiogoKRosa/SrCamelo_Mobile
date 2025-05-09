package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.UserRepositoryImpl
import com.srcamelo_kotlin.model.UserModel
import com.srcamelo_kotlin.network.Resource
import javax.inject.Inject

data class GetUserResult(val result: Resource<UserModel>)

class GetUserUseCase @Inject constructor(
    private val repository: UserRepositoryImpl
) {
    suspend operator fun invoke(id: String): GetUserResult {
        return GetUserResult(repository.getUserById(id))
    }
}

data class GetAllVendorsResult<T>(val result: Resource<T>)

data class GetAllVendorsUseCase @Inject constructor(
    private val repositoryImpl: UserRepositoryImpl
){
    suspend operator fun invoke(): GetAllVendorsResult<List<UserModel>>{
        return GetAllVendorsResult(repositoryImpl.getAllVendors() as Resource<List<UserModel>>)
    }
}