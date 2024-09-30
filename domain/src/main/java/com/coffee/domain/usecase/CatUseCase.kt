package com.coffee.domain.usecase

import android.util.Log
import com.coffee.data.models.CatData
import com.coffee.domain.repository.DomainRepositoryImpl
import javax.inject.Inject

class CatUseCase @Inject constructor(private val domainRepositoryImpl: DomainRepositoryImpl) {
    suspend fun getRandomCat(): CatData {
        val data = domainRepositoryImpl.getRandomCat()
        Log.e("12345", data.url)
        return data
    }
}