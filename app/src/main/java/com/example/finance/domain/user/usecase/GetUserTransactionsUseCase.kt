package com.example.finance.domain.user.usecase

import com.example.finance.data.response.user.response.Item
import com.example.finance.data.response.user.response.UserTransactionsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserTransactionsUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    val itemList = listOf(Item(10.1, 10, "hhf", "jdjjd", "hdhd"),
        Item(10.1, 10, "hhf", "jdjjd", "hdhd")
    )
    val userTransations = UserTransactionsResponse(dateFrom = "11.11.1111", dateTo = "11.12.1111", itemList)

    suspend operator fun invoke(id: String): UserTransactionsResponse {
        return withContext(dispatcher) {
            userRepository.getUserTransactions(id)
//            userTransations
        }
    }
}