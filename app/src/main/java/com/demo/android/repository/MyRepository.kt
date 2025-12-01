package com.demo.android.repository

import com.demo.android.api.ApiService
import com.demo.android.db.HoldingsDao
import com.demo.android.model.ApiResponse
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService,private val holdingsDao: HoldingsDao) {
    suspend fun getData(): ApiResponse {
        //holdingsDao.insertHolding(apiService.fetchData())
        return apiService.fetchData()
    }
}