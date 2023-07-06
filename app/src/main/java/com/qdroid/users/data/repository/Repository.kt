package com.qdroid.users.data.repository

import com.qdroid.chargingstations.network.ApiResult
import com.qdroid.users.database.UserDB
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUsers(): Flow<ApiResult<Any>>

    fun getUser(userId: Int): Flow<UserDB>
}