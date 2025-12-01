package com.demo.android.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.android.model.UserHolding

@Dao
interface HoldingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHoldings(list: List<UserHolding>)

    @Query("SELECT * FROM tbl_holding")
    fun getHoldings(): List<UserHolding>
}