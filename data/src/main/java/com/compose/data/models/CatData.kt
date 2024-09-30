package com.compose.data.models

data class RawCatData(
    val catDataList : List<CatData>
)

data class CatData(
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)
