package com.qdroid.users.data.datasource.local

import com.qdroid.users.database.UserDB

interface LocalDataSource {

    suspend fun getUsers(): List<UserDB>

    suspend fun saveUsers(users: List<UserDB>)

    suspend fun getUser(userId: Int): UserDB
}