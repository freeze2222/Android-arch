package com.compose.domain.repository

import com.compose.data.models.CatData
import com.compose.data.utils.Provider
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor() : DomainRepository {
    override suspend fun getRandomCat(): CatData {
        val request = Provider.provideRetrofit().getRandomCat().execute()
        val data = request.body()
        return if (request.isSuccessful && data != null) data.first() else CatData()
    }
}