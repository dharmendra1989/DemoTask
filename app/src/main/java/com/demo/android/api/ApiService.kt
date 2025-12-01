package com.demo.android.api

import com.demo.android.model.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun fetchData(): ApiResponse
}