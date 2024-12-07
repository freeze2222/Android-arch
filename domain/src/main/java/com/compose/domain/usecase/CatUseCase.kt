package com.compose.domain.usecase

import com.compose.data.models.CatData
import com.compose.domain.repository.DomainRepositoryImpl
import javax.inject.Inject

class CatUseCase @Inject constructor(private val domainRepositoryImpl: DomainRepositoryImpl) {
    suspend fun getRandomCat(): CatData {
        val data = domainRepositoryImpl.getRandomCat()
        return data
    }
    suspend fun addFavourite(data: CatData, isFavourited: Boolean) {
        domainRepositoryImpl.toggleFavouriteCat(data, isFavourited)
    }
    suspend fun getFavourites(): List<CatData> {
        return domainRepositoryImpl.getFavourites()
    }
}