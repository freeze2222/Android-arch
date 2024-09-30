package com.coffee.domain.repository

import com.coffee.data.models.CatData

interface DomainRepository {
    suspend fun getRandomCat(): CatData
}