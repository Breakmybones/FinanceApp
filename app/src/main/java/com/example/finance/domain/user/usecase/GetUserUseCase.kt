package com.example.finance.domain.user.usecase

import com.example.finance.domain.user.model.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(id: String): UserModel {
        return withContext(dispatcher) {
            userRepository.getUserById(id)
//            UserModel(
//                id = id,
//                email = "dino",
//                login = "Dinozavr",
//                firstName = "Dinozavr",
//                lastName = "Dinozavr",
//                phone = "88888888888888",
//                dateOfBirth = "1.1.1",
//                role = "Admin",
//                region = "Russia",
//                cardNumber = "888888888888",
//                balance = 100.0
//            )
        }
    }
}