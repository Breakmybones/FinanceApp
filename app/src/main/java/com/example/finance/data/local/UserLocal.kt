package com.example.finance.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserLocal (
    @PrimaryKey
    val id: String,
    val email: String?,
    val login : String?,
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val dateOfBirth: String?,
    val role: String?,
    val region: String?,
    val cardNumber: String?,
    val balance: Double?,
    )