package com.example.finance.domain.user.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PutTransferUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(countId: String, amount: Double, recipientCardNumber: String) {
        return withContext(dispatcher) {
            userRepository.putTransfer(countId, amount, recipientCardNumber)
        }
    }
}