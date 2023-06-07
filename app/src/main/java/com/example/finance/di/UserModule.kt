package com.example.finance.di

import com.example.finance.data.response.user.UserApi
import com.example.finance.data.response.user.UserRepositoryImpl
import com.example.finance.domain.user.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun provideUserRepository(
        userApi: UserApi
    ): UserRepository = UserRepositoryImpl(userApi)

    @Provides
    fun provideGetUserUseCase(
        repository: UserRepository
    ): GetUserUseCase = GetUserUseCase(repository)

    @Provides
    fun provideGetUserIdUseCase(
        repository: UserRepository
    ): GetUserIdUseCase = GetUserIdUseCase(repository)

    @Provides
    fun provideGetUserTransactionsUseCase(
        repository: UserRepository
    ): GetUserTransactionsUseCase = GetUserTransactionsUseCase(repository)

    @Provides
    fun providePutTransferUseCase(
        repository: UserRepository
    ): PutTransferUseCase = PutTransferUseCase(repository)
}