package com.example.finance.domain.user.usecase

import com.example.finance.data.response.user.response.UserTransactionsResponse
import com.example.finance.domain.user.model.UserModel

interface UserRepository {

    suspend fun getUserById(id: String): UserModel

    suspend fun getUserId(email: String, password: String): String

    suspend fun getUserTransactions(id: String): UserTransactionsResponse

    suspend fun putTransfer(accountId: String, amount: Double, recipientCardNumber: String)

}