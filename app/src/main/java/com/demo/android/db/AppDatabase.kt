package com.demo.android.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.android.model.UserHolding

@Database(entities = [UserHolding::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun holdingsDao(): HoldingsDao
}