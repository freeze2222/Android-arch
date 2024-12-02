package com.compose.domain.repository

import com.compose.data.models.CatData

interface DomainRepository {
    suspend fun getRandomCat(): CatData
    suspend fun addFavouriteCat()
    suspend fun deleteFavouriteCate()
    suspend fun getFavourites()
}