package com.example.finance.presentation.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.finance.R
import com.example.finance.data.local.UserLocal
import com.example.finance.data.local.UserLocalRepository
import com.example.finance.domain.user.model.UserModel
import com.example.finance.domain.user.usecase.GetUserIdUseCase
import com.example.finance.domain.user.usecase.GetUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    val getUserIdUseCase: GetUserIdUseCase,
    val getUserUseCase: GetUserUseCase
): ViewModel() {

    private val _userLiveData = MutableLiveData<UserModel?>(null)
    val userLiveData: LiveData<UserModel?>
        get() = _userLiveData

    private val _userIdLiveData = MutableLiveData<String?>(null)
    val userIdLiveData: LiveData<String?>
        get() = _userIdLiveData

    fun loginUser(context: Context, email: String, password: String, repository: UserLocalRepository, navController: NavController) {
        viewModelScope.launch {
            try {
                if (getUserIdUseCase(email, password).isNotEmpty()) {
                    _userIdLiveData.value = getUserIdUseCase(email, password)
                    _userLiveData.value = getUserUseCase(_userIdLiveData.value!!)
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
                    navController.navigate(R.id.homeFragment)
                }
            }
            catch (error: Throwable) {
                Log.e("error", error.toString())
                Log.e("user", getUserIdUseCase("jjfjf", "jkfkj"))
                Toast.makeText(context, "Такого пользователя не существует", Toast.LENGTH_SHORT).show()
            }

        }
    }


    companion object {
        fun provideFactory(
            getUserIdUseCase: GetUserIdUseCase,
            getUserUseCase: GetUserUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    getUserIdUseCase,
                    getUserUseCase
                )
            }
        }
    }
}