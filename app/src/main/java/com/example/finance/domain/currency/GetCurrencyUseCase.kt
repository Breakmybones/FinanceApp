package com.example.finance.domain.currency

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCurrencyUseCase(
    private val currencyRepository: CurrencyRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): CurrencyModel {
        return withContext(dispatcher) {
            currencyRepository.getCurrency()
        }
    }
}