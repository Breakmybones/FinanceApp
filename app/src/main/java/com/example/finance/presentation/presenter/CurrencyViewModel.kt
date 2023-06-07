package com.example.finance.presentation.presenter

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finance.domain.currency.CurrencyModel
import com.example.finance.domain.currency.GetCurrencyUseCase
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val getCurrencyUseCase: GetCurrencyUseCase
): ViewModel() {

    private val _currencyInfo = MutableLiveData<CurrencyModel?>(null)
    val currencyInfo: LiveData<CurrencyModel?>
        get()  = _currencyInfo

    fun loadCurrency() {
        viewModelScope.launch {
            try {
                if (!getCurrencyUseCase().usd.isNaN()){
                    Log.e("currency", getCurrencyUseCase().usd.toString())
                    _currencyInfo.value = getCurrencyUseCase()
                }

            }
            catch (error: Throwable) {
                Log.e("error", error.toString())
            }
        }
    }

    companion object {
        fun provideFactory(
            getCurrencyUseCase: GetCurrencyUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CurrencyViewModel(
                    getCurrencyUseCase
                )
            }
        }
    }
}