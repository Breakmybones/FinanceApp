package com.example.finance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TransactionDao {

    @Insert(onConflict = REPLACE)
    suspend fun addTransaction(transaction: TransactionLocal)

    @Query("SELECT * FROM trans")
    suspend fun getTransactions(): List<TransactionLocal?>

    @Query("DELETE FROM trans")
    suspend fun deleteAllTransactions()
}