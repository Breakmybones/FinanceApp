package com.example.finance.domain.currency

interface CurrencyRepository {

    suspend fun getCurrency(): CurrencyModel
}