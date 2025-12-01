package com.demo.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_holding")
data class UserHolding(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val avgPrice: Double,
    val close: Double,
    val ltp: Double,
    val quantity: Int,
    val symbol: String
)