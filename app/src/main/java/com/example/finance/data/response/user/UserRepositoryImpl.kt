package com.example.finance.data.response.user

import com.example.finance.data.response.user.response.TransferData
import com.example.finance.data.response.user.response.UserTransactionsResponse
import com.example.finance.domain.user.model.UserModel
import com.example.finance.domain.user.usecase.UserRepository

class UserRepositoryImpl(
    private val api: UserApi
): UserRepository {
    override suspend fun getUserById(id: String): UserModel {
        val responseFirst = api.getUser(id)
        val responseSecond = api.getUserAccount(id)
        return UserModel(
            id = responseFirst.id,
            email = responseFirst.email,
            login =  responseFirst.login,
            firstName = responseFirst.firstName,
            lastName = responseFirst.lastName,
            phone = responseFirst.phone,
            dateOfBirth = responseFirst.dateOfBirth,
            role = responseFirst.role,
            region = responseFirst.region,
            cardNumber = responseSecond.cardNumber,
            balance = responseSecond.balance,
        )
    }

    override suspend fun getUserId(email: String, password: String): String {
        return api.getUserId(email, password)
    }

    override suspend fun getUserTransactions(id: String): UserTransactionsResponse {
        return api.getUserTransactions(id)
    }

    override suspend fun putTransfer(
        accountId: String,
        amount: Double,
        recipientCardNumber: String,
    ) {
        api.transfer(TransferData(accountId, amount, recipientCardNumber))
    }
}