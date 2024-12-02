package com.compose.domain.repository

import com.compose.data.models.CatData
import com.compose.data.utils.Provider
import com.compose.data.utils.RetrofitApi
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val retrofitApi: RetrofitApi
) : DomainRepository {
    override suspend fun getRandomCat(): CatData {
        val request = retrofitApi.getRandomCat().execute()
        val data = request.body()
        return if (request.isSuccessful && data != null) data.first() else CatData()
    }

    override suspend fun addFavouriteCat() {

    }

    override suspend fun deleteFavouriteCate() {
        TODO("Not yet implemented")
    }

    override suspend fun getFavourites() {
        TODO("Not yet implemented")
    }

}