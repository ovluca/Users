package com.qdroid.users.data.datasource.remote

import com.qdroid.users.network.model.ApiResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getUsers(): Response<ApiResponse>
}