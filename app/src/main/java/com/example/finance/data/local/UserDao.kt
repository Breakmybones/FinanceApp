package com.example.finance.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun addUser(user: UserLocal)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserLocal?

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}