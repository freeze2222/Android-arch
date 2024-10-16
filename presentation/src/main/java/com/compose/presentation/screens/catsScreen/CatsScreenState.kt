package com.compose.presentation.screens.catsScreen

import com.compose.data.models.CatData


data class CatsScreenState(
    var url: String = "",
    var width: Int = 0,
    var height: Int = 0
){
    fun deserialize(o: CatData) {
        this.url = o.url
        this.width = o.width
        this.height = o.height
    }

}