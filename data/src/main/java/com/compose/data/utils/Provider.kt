package com.compose.data.utils

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Provider {
    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/images/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitApi::class.java)
    }
    /*@Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context){
        Room.databaseBuilder(
            context,
            CatDatabase::class.java, "database-name"
        ).build()
    }*/
}