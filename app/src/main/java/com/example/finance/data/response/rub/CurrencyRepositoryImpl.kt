package com.example.finance.data.response.rub

import android.util.Log
import com.example.finance.domain.currency.CurrencyModel
import com.example.finance.domain.currency.CurrencyRepository

class CurrencyRepositoryImpl(
    private val api: RubApi
): CurrencyRepository {
    override suspend fun getCurrency(): CurrencyModel {
        val response = api.getRub()
        return CurrencyModel(
            usd = convertHashMapToList(response.valute)[13][0].value,
            eur = convertHashMapToList(response.valute)[14][0].value,
            gbp = convertHashMapToList(response.valute)[2][0].value
        )

    }
}