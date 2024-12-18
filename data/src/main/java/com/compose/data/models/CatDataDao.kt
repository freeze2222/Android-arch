package com.compose.data.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatDataDao {
    @Query("SELECT * FROM catdata")
    fun getFavourites(): List<CatData>

    @Insert
    fun insert(user: CatData)

    @Delete
    fun delete(user: CatData)
}