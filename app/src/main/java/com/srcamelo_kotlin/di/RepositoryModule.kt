package com.srcamelo_kotlin.di

import android.content.Context
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.data.repository.LoginRepositoryImpl
import com.srcamelo_kotlin.data.repository.UserRepositoryImpl
import com.srcamelo_kotlin.network.SrcameloApiService
import com.srcamelo_kotlin.ui.use_case.CreateClientUseCase
import com.srcamelo_kotlin.ui.use_case.LoginUseCase
import com.srcamelo_kotlin.ui.viewModel.LoginViewModel
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    //DataStore
    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context):DataStoreManager{
        return DataStoreManager(context)
    }

    //API
    @Provides
    fun provideSrcamloApi():SrcameloApiService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:8000")
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
    fun provideUserViewModel(useCase: CreateClientUseCase): UsersViewModel{
        return UsersViewModel(useCase)
    }
}