package com.demo.android.repository

import android.content.Context
import com.demo.android.api.ApiService
import com.demo.android.db.HoldingsDao
import com.demo.android.model.UserHolding
import com.demo.android.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val apiService: ApiService,
    private val holdingsDao: HoldingsDao,
    @ApplicationContext private val context: Context
) {
    suspend fun getData(): List<UserHolding> {
        try {
            if (Constants.isNetworkAvailable(context)) {
                holdingsDao.insertHoldings(apiService.fetchData().data.userHolding)
                return apiService.fetchData().data.userHolding
            } else {
                return holdingsDao.getHoldings()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return holdingsDao.getHoldings()
        }
    }
}