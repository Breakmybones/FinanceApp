package com.example.finance.domain.user.model

data class UserModel(
    val id: String?,
    val email: String?,
    val login : String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val dateOfBirth: String,
    val role: String,
    val region: String,
    val cardNumber: String,
    val balance: Double,
)