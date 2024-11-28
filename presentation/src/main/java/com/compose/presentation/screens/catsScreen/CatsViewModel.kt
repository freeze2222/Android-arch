package com.compose.presentation.screens.catsScreen

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
class CatsViewModel @Inject constructor(private val useCase: CatUseCase) : ViewModel() {
    private val _state =
        MutableStateFlow(ArrayList<ImageData>())

    val state: StateFlow<List<ImageData>>
        get() = _state.asStateFlow()

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            val newState = _state.value
            // newState.removeLast()
            val element = ImageData()
            element.deserialize(useCase.getRandomCat())
            newState.add(element)
            _state.emit(newState)
        }
    }
    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (_state.value.size < 10) {
                val newState = _state.value
                val element = ImageData()
                element.deserialize(useCase.getRandomCat())
                newState.add(element)
                _state.emit(newState)
            }
        }
    }
}