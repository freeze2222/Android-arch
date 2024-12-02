package com.compose.presentation.screens.catsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.domain.usecase.CatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(private val useCase: CatUseCase) : ViewModel() {
    private val _state = MutableStateFlow(CatsScreenState())

    val state: StateFlow<CatsScreenState>
        get() = _state.asStateFlow()

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            val element = ImageData().apply { deserialize(useCase.getRandomCat()) }
            _state.emit(CatsScreenState(_state.value.data.plusElement(element)))
        }
    }

    fun addFavourite(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(
                CatsScreenState(_state.value.data.clone()
                    .apply { this[index].isFavourited = !this[index].isFavourited })
            )
        }
    }

    init {
        updateList()
    }
}