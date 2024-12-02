package com.compose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatData(
    @PrimaryKey val uid: Int = 0,
    @ColumnInfo val url: String = "",
    @ColumnInfo val width: Int = 0,
    @ColumnInfo val height: Int = 0
)
