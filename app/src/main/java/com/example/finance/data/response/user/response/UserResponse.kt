package com.example.finance.data.response.user.response

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String
        )

data class UserAccountResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("cardNumber")
    val cardNumber: String
)