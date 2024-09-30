package com.coffee.data.utils

import com.coffee.data.models.CatData
import com.coffee.data.models.RawCatData
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("search")
    fun getRandomCat(): Call<List<CatData>>
}