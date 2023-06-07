package com.example.finance.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserLocal::class, TransactionLocal::class], version = 2)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getTransactionDao(): TransactionDao
}