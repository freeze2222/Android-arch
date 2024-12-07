package com.compose.presentation.screens.favouriteScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.domain.usecase.CatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteCatsViewModel @Inject constructor(private val useCase: CatUseCase) : ViewModel() {
    private val _state = MutableStateFlow(FavouriteCatsScreenState())

    val state: StateFlow<FavouriteCatsScreenState>
        get() = _state.asStateFlow()

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            val allFavCats = useCase.getFavourites()
            val catImages = ArrayList<ImageData>()
            allFavCats.forEach { value ->
                catImages.add(ImageData().apply { deserialize(value, true) })
            }
            _state.emit(FavouriteCatsScreenState(catImages.toTypedArray()))
        }
    }

    fun toggleFavourite(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val cats = _state.value.data.clone()
            if (cats.isEmpty())
                return@launch
            val isFavourited = !cats[index].isFavourited
            cats.apply { this[index].isFavourited = isFavourited }
            useCase.addFavourite(cats[index].toPojo(), isFavourited)
            _state.emit(
                FavouriteCatsScreenState(cats)
            )
        }
    }

    init {
        updateList()
    }
}