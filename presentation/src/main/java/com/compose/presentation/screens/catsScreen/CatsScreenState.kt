package com.compose.presentation.screens.catsScreen

import com.compose.data.models.CatData


data class ImageData(
    var url: String = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.77sFQJnf98We_UWwjitL3wHaHa%26pid%3DApi&f=1&ipt=63f053a9413d8c893a8f01a5c0206514c8513d79824312d9614b17d764ad659f&ipo=images",
    var width: Int = 0,
    var height: Int = 0,
    var offset: Int = 0,
    var isFavourited: Boolean = false
){
    fun deserialize(o: CatData) {
        this.url = o.url
        this.width = o.width
        this.height = o.height
    }
}

data class CatsScreenState(
    val data: Array<ImageData> = Array(1){ImageData()}
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatsScreenState

        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}