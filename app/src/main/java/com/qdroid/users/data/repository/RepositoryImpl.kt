package com.qdroid.users.data.repository

import com.qdroid.chargingstations.network.ApiResult
import com.qdroid.users.data.datasource.local.LocalDataSource
import com.qdroid.users.data.datasource.remote.RemoteDataSource
import com.qdroid.users.database.UserDB
import com.qdroid.users.network.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : Repository {

    override fun getUsers(): Flow<ApiResult<Any>> = flow {
        emit(ApiResult.Loading(true))
        try {
            val response = remoteDataSource.getUsers()
            if (response.isSuccessful) {
                val usersDB = response.body()?.data?.map { user: User ->
                    UserDB(
                        id = user.id,
                        email = user.email ?: "",
                        firstName = user.firstName ?: "",
                        lastName = user.lastName ?: "",
                        avatar = user.avatar ?: ""

                    )
                }
                usersDB?.let { localDataSource.saveUsers(users = it) }
                emit(ApiResult.Success(usersDB))
            } else {
                if (localDataSource.getUsers().isNotEmpty()) {
                    emit(ApiResult.Success(localDataSource.getUsers()))
                } else {
                    emit(ApiResult.Error("No Users"))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (localDataSource.getUsers().isNotEmpty()) {
                emit(ApiResult.Success(localDataSource.getUsers()))
            } else {
                emit(ApiResult.Error("No Users"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getUser(userId: Int): Flow<UserDB> = flow {
        emit(localDataSource.getUser(userId))
    }.flowOn(Dispatchers.IO)

}