package com.qdroid.users.data.datasource.remote

import com.qdroid.users.network.ApiService
import com.qdroid.users.network.model.ApiResponse
import com.qdroid.users.network.model.User
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getUsers(): Response<ApiResponse> =
        apiService.getUsers(perPage = 10)

}