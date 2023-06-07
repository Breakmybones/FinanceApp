package com.example.finance.presentation.presenter

import android.content.Context
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finance.data.local.TransactionLocal
import com.example.finance.data.local.UserLocal
import com.example.finance.data.local.UserLocalRepository
import com.example.finance.data.response.user.response.UserTransactionsResponse
import com.example.finance.domain.user.model.UserModel
import com.example.finance.domain.user.usecase.GetUserIdUseCase
import com.example.finance.domain.user.usecase.GetUserTransactionsUseCase
import com.example.finance.domain.user.usecase.GetUserUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    val getUserUseCase: GetUserUseCase,
    val getUserTransactionsUseCase: GetUserTransactionsUseCase
    ): ViewModel() {

    private val _userLiveData = MutableLiveData<UserModel?>(null)
    val userLiveData: LiveData<UserModel?>
        get() = _userLiveData

    private val _userTransactionsLiveData = MutableLiveData<UserTransactionsResponse?>(null)
    val userTransactionsLiveData: LiveData<UserTransactionsResponse?>
        get() = _userTransactionsLiveData

    fun getUserCard(repository: UserLocalRepository) {
        viewModelScope.launch {
            val userBd = repository.getUser()
            _userLiveData.value = UserModel(
                id = userBd?.id,
                email = userBd?.email,
                login = userBd?.login!!,
                firstName = userBd.firstName!!,
                lastName = userBd.lastName!!,
                phone = userBd.phone!!,
                dateOfBirth = userBd.dateOfBirth!!,
                role = userBd.role!!,
                region = userBd.region!!,
                cardNumber = userBd.cardNumber!!,
                balance = userBd.balance!!
            )
        }
    }

    fun getUserFromApi(context: Context, repository: UserLocalRepository) {
        viewModelScope.launch {
            val userIdFromDataBase = repository.getUser()?.id
            try {
                if (!(getUserUseCase(userIdFromDataBase!!).email.isNullOrEmpty())) {
                    _userLiveData.value = getUserUseCase(userIdFromDataBase)
                    repository.addUser(
                        UserLocal(
                            id = _userLiveData.value!!.id!!,
                            email = _userLiveData.value?.email,
                            login = _userLiveData.value?.login,
                            firstName = _userLiveData.value?.firstName,
                            lastName = _userLiveData.value?.lastName,
                            phone = _userLiveData.value?.phone,
                            dateOfBirth = _userLiveData.value?.dateOfBirth,
                            role = _userLiveData.value?.role,
                            region = _userLiveData.value?.region,
                            cardNumber = _userLiveData.value?.cardNumber,
                            balance = _userLiveData.value?.balance
                        )
                    )
                }
            }
            catch (error: Throwable) {
                Timber.tag("error").e(error.toString())
            }
        }
    }

    fun getUserTransactionsFromApi(repository: UserLocalRepository) {
        viewModelScope.launch {
            val userIdFromDataBase = repository.getUser()?.id
            try {
                if (getUserTransactionsUseCase(userIdFromDataBase!!).items?.isNotEmpty() == true) {
                    _userTransactionsLiveData.value = getUserTransactionsUseCase(userIdFromDataBase)
                    val listOfTransactions = mutableListOf<TransactionLocal>()
                    for (transaction in _userTransactionsLiveData.value!!.items!!) {
                        listOfTransactions.add(TransactionLocal(
                            1,
                            transaction?.amount,
                            transaction?.transactionType,
                            transaction?.cashback,
                            transaction?.credentials,
                            transaction?.timestamp))
                    }
                    repository.addTransactions(listOfTransactions)
                }
            }
            catch (error: Throwable) {
                Timber.tag("error").e(error.toString())
            }
        }
    }

    companion object {
        fun provideFactory(
            getUserUseCase: GetUserUseCase,
            getUserTransactionsUseCase: GetUserTransactionsUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    getUserUseCase,
                    getUserTransactionsUseCase
                )
            }
        }
    }
}