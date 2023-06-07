package com.example.finance.data.response.user.response


import com.google.gson.annotations.SerializedName

data class UserTransactionsResponse(
    @SerializedName("dateFrom")
    val dateFrom: String?,
    @SerializedName("dateTo")
    val dateTo: String?,
    @SerializedName("items")
    val items: List<Item?>?
)

data class Item(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("cashback")
    val cashback: Int?,
    @SerializedName("credentials")
    val credentials: String?,
    @SerializedName("timestamp")
    val timestamp: String?,
    @SerializedName("transactionType")
    val transactionType: String?
)