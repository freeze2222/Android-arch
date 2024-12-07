package com.compose.domain.repository

import com.compose.data.models.CatData

interface DomainRepository {
    suspend fun getRandomCat(): CatData
    suspend fun toggleFavouriteCat(data: CatData, isFavourited: Boolean)
    suspend fun getFavourites(): List<CatData>
}