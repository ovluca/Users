package com.qdroid.users.data.datasource.local

import com.qdroid.users.database.UserDB
import com.qdroid.users.database.UserDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : LocalDataSource {

    override suspend fun getUsers(): List<UserDB> = userDao.getUsers()

    override suspend fun saveUsers(users: List<UserDB>) = userDao.insertUsers(users = users)

    override suspend fun getUser(userId: Int): UserDB = userDao.getUser(userId = userId)
}