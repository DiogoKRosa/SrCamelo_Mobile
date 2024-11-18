package com.srcamelo_kotlin.ui.use_case

import com.srcamelo_kotlin.data.repository.UserRepositoryImpl
import com.srcamelo_kotlin.model.BannerModel
import com.srcamelo_kotlin.model.Id
import com.srcamelo_kotlin.network.Resource
import okhttp3.MultipartBody
import javax.inject.Inject

data class UpdateVendorBannerResult(
    val result: Resource<Any>? = null
)

class UpdateVendorBannerUseCase @Inject constructor(
    private val repository: UserRepositoryImpl
) {
    suspend operator fun invoke(
        userId: String,
        bannerUrl: MultipartBody.Part? = null,
        fantasyName: String,
        paymentMethods:  List<String>
    ): UpdateVendorBannerResult {
        val result = BannerModel(
            Id(userId),
            fantasyName,
            paymentMethods
        )
        return UpdateVendorBannerResult(repository.updateBannerVendor(userId = userId ,result, bannerUrl))
    }
}