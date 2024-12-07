package com.compose.presentation.screens.favouriteScreen

import com.compose.data.models.CatData


data class ImageData(
    var url: String = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.77sFQJnf98We_UWwjitL3wHaHa%26pid%3DApi&f=1&ipt=63f053a9413d8c893a8f01a5c0206514c8513d79824312d9614b17d764ad659f&ipo=images",
    var width: Int = 0,
    var height: Int = 0,
    var offset: Int = 0,
    var isFavourited: Boolean = false
){
    fun deserialize(o: CatData, isFavourited: Boolean) {
        this.url = o.url
        this.width = o.width
        this.height = o.height
        this.isFavourited = isFavourited
    }
    fun toPojo():CatData{
        return CatData(url = this.url, width = this.width, height = this.height)
    }
}

data class FavouriteCatsScreenState(
    val data: Array<ImageData> = Array(1){ImageData(isFavourited = true)}
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FavouriteCatsScreenState

        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}