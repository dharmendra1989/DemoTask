package com.demo.android.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.android.model.ApiResponse
import com.demo.android.model.UserHolding
import kotlinx.coroutines.flow.Flow

@Dao
interface HoldingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHoldings(list: List<UserHolding>)

    @Query("SELECT * FROM tbl_holding")
    fun getHoldings(): LiveData<List<UserHolding>>
}