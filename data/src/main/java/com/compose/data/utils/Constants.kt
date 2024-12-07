package com.compose.data.utils

object Constants {
    enum class NavDestinations{
        CATS_SCREEN,
        FAVOURITE_SCREEN,;

        fun toLocalizedName(): String {
            return when(this){
                CATS_SCREEN -> "Домой"
                FAVOURITE_SCREEN -> "Избранное"
            }
        }
    }
}