package com.srcamelo_kotlin.di

import com.srcamelo_kotlin.data.repository.UserRepositoryImpl
import com.srcamelo_kotlin.domain.repository.UserRepository
import com.srcamelo_kotlin.network.SrcameloApiService
import com.srcamelo_kotlin.ui.use_case.CreateClientUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideCreateClientUseCase(repository: UserRepositoryImpl): CreateClientUseCase {
        return CreateClientUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesRepository(apiService: SrcameloApiService): UserRepository{
        return UserRepositoryImpl(
            apiService = apiService
        )
    }
}
