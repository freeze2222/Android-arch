package com.compose.domain.repository

import com.compose.data.models.CatData
import com.compose.data.models.CatDataDao
import com.compose.data.utils.RetrofitApi
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val catDao: CatDataDao
) : DomainRepository {
    override suspend fun getRandomCat(): CatData {
        val request = retrofitApi.getRandomCat().execute()
        val data = request.body()
        return if (request.isSuccessful && data != null) data.first() else CatData()
    }

    override suspend fun toggleFavouriteCat(data: CatData, isFavourited: Boolean) {
        if (isFavourited)
            catDao.insert(data)
        else
            catDao.delete(data)
    }

    override suspend fun getFavourites(): List<CatData> {
        return catDao.getFavourites()
    }

}