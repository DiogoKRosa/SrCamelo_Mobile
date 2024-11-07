package com.srcamelo_kotlin.di

import com.srcamelo_kotlin.repository.CreateUserRepository
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
    fun provideCreateClientUseCase(repository: CreateUserRepository): CreateClientUseCase {
        return CreateClientUseCase(repository)
    }
}
