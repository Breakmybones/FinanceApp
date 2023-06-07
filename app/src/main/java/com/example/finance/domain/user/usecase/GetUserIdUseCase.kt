package com.example.finance.domain.user.usecase

import com.example.finance.domain.user.model.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserIdUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(email: String, password: String): String {
        return withContext(dispatcher) {
            userRepository.getUserId(email, password)
        }
    }
}