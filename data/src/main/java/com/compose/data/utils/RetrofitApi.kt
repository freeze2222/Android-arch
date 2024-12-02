package com.compose.data.utils

import com.compose.data.models.CatData
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("search")
    fun getRandomCat(): Call<List<CatData>>
}