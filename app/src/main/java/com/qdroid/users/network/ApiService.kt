package com.qdroid.users.network

import com.qdroid.users.network.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(@Query("per_page") perPage: Int): Response<ApiResponse>
}