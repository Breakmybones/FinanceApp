package com.example.finance.data.local

import android.content.Context
import androidx.room.Room

class UserLocalRepository(context: Context) {

    val db by lazy {
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private const val DATABASE_NAME = "finance.db"
    }

    private val userDao by lazy {
        db.getUserDao()
    }

    private val transactionDao by lazy {
        db.getTransactionDao()
    }

    suspend fun addUser(user: UserLocal) {
        userDao.addUser(user)
    }

    suspend fun deleteUser() {
        userDao.deleteUser()
    }

    suspend fun getUser(): UserLocal? {
        return userDao.getUser()
    }

    suspend fun updateUserWithApi(user: UserLocal) {
        userDao.deleteUser()
        userDao.addUser(user)
    }

    suspend fun getTransactions(): List<TransactionLocal?> {
        return transactionDao.getTransactions()
    }

    suspend fun addTransactions(listOfTransactions: List<TransactionLocal>) {
        transactionDao.deleteAllTransactions()
        for (transaction in listOfTransactions) {
            transactionDao.addTransaction(transaction)
        }
    }
}