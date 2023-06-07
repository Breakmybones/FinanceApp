package com.example.finance.presentation.presenter

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finance.data.local.TransactionLocal
import com.example.finance.data.local.UserLocalRepository
import com.example.finance.data.response.user.response.UserTransactionsResponse
import com.example.finance.domain.user.usecase.GetUserTransactionsUseCase
import com.example.finance.domain.user.usecase.GetUserUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class TransfersViewModel(
    val getUserTransactionsUseCase: GetUserTransactionsUseCase
): ViewModel() {
    private val _userTransactionsLiveData = MutableLiveData<UserTransactionsResponse?>(null)
    val userTransactionsLiveData: LiveData<UserTransactionsResponse?>
        get() = _userTransactionsLiveData

    private val _userTransactionsBdLiveData = MutableLiveData<List<TransactionLocal?>>(null)
    val userTransactionsBdLiveData: LiveData<List<TransactionLocal?>>
        get() = _userTransactionsBdLiveData

    fun getUserTransactionsFromApi(repository: UserLocalRepository) {
        viewModelScope.launch {
            val userIdFromDataBase = repository.getUser()?.id
            try {
                if (getUserTransactionsUseCase(userIdFromDataBase!!).items?.isNotEmpty() == true) {
                    _userTransactionsLiveData.value = getUserTransactionsUseCase(userIdFromDataBase)
                    val listOfTransactions = mutableListOf<TransactionLocal>()
                    for (transaction in _userTransactionsLiveData.value!!.items!!) {
                        listOfTransactions.add(TransactionLocal(
                            0,
                            transaction?.amount,
                            transaction?.transactionType,
                            transaction?.cashback,
                            transaction?.credentials,
                            transaction?.timestamp))
                    }
                    repository.addTransactions(listOfTransactions)
                    _userTransactionsBdLiveData.value = repository.getTransactions()
                }
            }
            catch (error: Throwable) {
                Timber.tag("error").e(error.toString())
            }
        }
    }

    companion object {
        fun provideFactory(
            getUserTransactionsUseCase: GetUserTransactionsUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                TransfersViewModel(
                    getUserTransactionsUseCase
                )
            }
        }
    }
}