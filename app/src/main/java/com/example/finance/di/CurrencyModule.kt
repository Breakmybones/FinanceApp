package com.example.finance.di

import com.example.finance.data.response.rub.CurrencyRepositoryImpl
import com.example.finance.data.response.rub.RubApi
import com.example.finance.domain.currency.CurrencyRepository
import com.example.finance.domain.currency.GetCurrencyUseCase
import dagger.Module
import dagger.Provides

@Module
class CurrencyModule {

    @Provides
    fun provideCurrencyRepository(
        rubApi: RubApi
    ): CurrencyRepository = CurrencyRepositoryImpl(rubApi)

    @Provides
    fun provideGetCurrencyUseCase(
        repository: CurrencyRepository
    ): GetCurrencyUseCase = GetCurrencyUseCase(repository)
}