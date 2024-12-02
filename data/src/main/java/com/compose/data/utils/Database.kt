package com.compose.data.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.data.models.CatData
import com.compose.data.models.CatDataDao

@Database(entities = [CatData::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun favouriteDao(): CatDataDao
}