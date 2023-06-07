package com.example.finance.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trans")
data class TransactionLocal (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amount: Double?,
    val transactionType: String?,
    val cashback: Int?,
    val credentials: String?,
    val timestamp: String?
        )