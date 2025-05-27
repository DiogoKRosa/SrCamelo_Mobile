package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.LocationRepositoryImpl
import com.srcamelo_kotlin.model.LocationModel
import com.srcamelo_kotlin.network.Resource
import javax.inject.Inject

data class UpdateLocationResult(
    val result: Resource<Any>? = null
)

class UpdateLocationUseCase @Inject constructor(
    private val repositoryImpl: LocationRepositoryImpl
){
    suspend operator fun invoke(
        userId: String,
        latitude: Double,
        longitude: Double
    ): UpdateLocationResult{
        val request = LocationModel(
            userId = userId, latitude = latitude, longitude = longitude
        )
        return UpdateLocationResult(result = repositoryImpl.updateLocation(request) )
    }
}

class GetLocationUseCase @Inject constructor(
    private val repositoryImpl: LocationRepositoryImpl
){
    suspend operator fun invoke(
        loginId: String
    ): Resource<List<LocationModel>>{
        return repositoryImpl.getAllLocation(loginId)
    }
}