package com.coffee.presentation.screens.catsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coffee.domain.usecase.CatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jline.utils.Log
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(private val useCase: CatUseCase) : ViewModel() {
    private val _state = MutableStateFlow(CatsScreenState())

    val state: StateFlow<CatsScreenState>
        get() = _state.asStateFlow()

    fun getRandomCat() {
        viewModelScope.launch(Dispatchers.IO) {
            val newState = CatsScreenState()
            newState.deserialize(useCase.getRandomCat())
            _state.emit(newState)
        }
    }

    init {
        getRandomCat()
    }
}