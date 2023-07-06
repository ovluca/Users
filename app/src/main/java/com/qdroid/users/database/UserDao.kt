package com.qdroid.users.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<UserDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserDB>)

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: Int): UserDB
}