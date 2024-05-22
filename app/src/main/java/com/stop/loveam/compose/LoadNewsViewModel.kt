package com.stop.loveam.compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoadNewsViewModel : ViewModel() {
    private val _stateFlow: MutableStateFlow<LoadNewsState> = MutableStateFlow(LoadNewsState())

    val stateFlow: MutableStateFlow<LoadNewsState> = _stateFlow

    fun loadNews() {
        // TODO
    }

    fun refresh() {
        // TODO
    }

    fun retry() {
        // TODO
    }
}

class LoadNewsState {
    val isLoading: Boolean = false
    val isError: Boolean = false
    val isSuccess: Boolean = false
}
