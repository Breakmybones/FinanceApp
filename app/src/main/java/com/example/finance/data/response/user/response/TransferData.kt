package com.example.finance.data.response.user.response


import com.google.gson.annotations.SerializedName

data class TransferData(
    @SerializedName("accountId")
    val accountId: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("recipientCardNumber")
    val recipientCardNumber: String?
)