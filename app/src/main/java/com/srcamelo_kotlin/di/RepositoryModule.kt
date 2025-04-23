package com.srcamelo_kotlin.di

import android.app.Application
import android.content.Context
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.data.repository.LocationRepositoryImpl
import com.srcamelo_kotlin.data.repository.LoginRepositoryImpl
import com.srcamelo_kotlin.data.repository.ProductRepositoryImpl
import com.srcamelo_kotlin.data.repository.UserRepositoryImpl
import com.srcamelo_kotlin.network.SrcameloApiService
import com.srcamelo_kotlin.ui.use_case.CreateClientUseCase
import com.srcamelo_kotlin.ui.use_case.CreateProductUseCase
import com.srcamelo_kotlin.ui.use_case.DeleteProductUseCase
import com.srcamelo_kotlin.ui.use_case.GetProductUseCase
import com.srcamelo_kotlin.ui.use_case.GetUserUseCase
import com.srcamelo_kotlin.ui.use_case.LoginUseCase
import com.srcamelo_kotlin.ui.use_case.UpdateLocationUseCase
import com.srcamelo_kotlin.ui.use_case.UpdateProductUseCase
import com.srcamelo_kotlin.ui.use_case.UpdateVendorBannerUseCase
import com.srcamelo_kotlin.ui.viewModel.LoginViewModel
import com.srcamelo_kotlin.ui.viewModel.ProductViewModel
import com.srcamelo_kotlin.ui.viewModel.UpdateLocationViewModel
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    //DataStore
    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context):DataStoreManager{
        return DataStoreManager.getInstance(context)
    }

    //API
    @Provides
    fun provideSrcamloApi():SrcameloApiService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(SrcameloApiService::class.java)
    }

    //Login Repository
    @Provides
    fun provideLoginRepositoryImpl(apiService: SrcameloApiService, preferences: DataStoreManager):LoginRepositoryImpl{
        return LoginRepositoryImpl(apiService, preferences)
    }

    @Provides
    fun provideLoginUseCase(repository: LoginRepositoryImpl): LoginUseCase{
        return LoginUseCase(repository)
    }

    @Provides
    fun provideLoginViewModel(useCase: LoginUseCase): LoginViewModel{
        return LoginViewModel(useCase)
    }

    //Create User Repository
    @Provides
    fun provideUserRepositoryImpl(apiService: SrcameloApiService): UserRepositoryImpl{
        return UserRepositoryImpl(apiService)
    }

    @Provides
    fun provideUserUseCase(repository: UserRepositoryImpl): CreateClientUseCase{
        return CreateClientUseCase(repository)
    }
    @Provides
    fun provideUpdateBannerVendorUseCase(repository: UserRepositoryImpl): UpdateVendorBannerUseCase{
        return UpdateVendorBannerUseCase(repository)
    }
    @Provides
    fun getUserUseCase(repository: UserRepositoryImpl): GetUserUseCase{
        return GetUserUseCase(repository)
    }

    @Provides
    fun provideUserViewModel(useCase: CreateClientUseCase, updateVendorBannerUseCase: UpdateVendorBannerUseCase, getUserUseCase: GetUserUseCase): UsersViewModel{
        return UsersViewModel(useCase, updateVendorBannerUseCase, getUserUseCase)
    }

    @Provides
    fun provideProductRepositoryImpl(apiService: SrcameloApiService): ProductRepositoryImpl {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    fun provideProductUseCase(repository: ProductRepositoryImpl): CreateProductUseCase {
        return CreateProductUseCase(repository)
    }
    @Provides
    fun provideGetProductUseCase(repository: ProductRepositoryImpl): GetProductUseCase {
        return GetProductUseCase(repository)
    }
    @Provides
    fun provideDeleteProductUseCase(repository: ProductRepositoryImpl): DeleteProductUseCase {
        return DeleteProductUseCase(repository)
    }
    @Provides
    fun provideUpdateProductUseCase(repository: ProductRepositoryImpl): UpdateProductUseCase {
        return UpdateProductUseCase(repository)
    }

    @Provides
    fun provideProductViewModel(createUseCase: CreateProductUseCase, getUseCase: GetProductUseCase, deleteProductUseCase: DeleteProductUseCase, updateProductUseCase: UpdateProductUseCase): ProductViewModel {
        return ProductViewModel(createUseCase, getUseCase, deleteProductUseCase, updateProductUseCase)
    }

    @Provides
    fun provideUpdateLocationViewModel(application: Application, dataStoreManager: DataStoreManager, updateLocationUseCase: UpdateLocationUseCase): UpdateLocationViewModel {
        return UpdateLocationViewModel(application, dataStoreManager, updateLocationUseCase)
    }

    @Provides
    fun provideUpdateLocationUseCase(repository: LocationRepositoryImpl): UpdateLocationUseCase{
        return UpdateLocationUseCase(repository)
    }

    @Provides
    fun provideLocationRepository(apiService: SrcameloApiService, preferences: DataStoreManager): LocationRepositoryImpl{
        return LocationRepositoryImpl(apiService, preferences)
    }
}