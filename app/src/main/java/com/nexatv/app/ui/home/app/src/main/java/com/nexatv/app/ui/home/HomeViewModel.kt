package com.nexatv.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexatv.app.data.NexaRepository
import com.nexatv.app.data.model.MediaItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val trendingMovies: List<MediaItem> = emptyList(),
    val trendingTv: List<MediaItem> = emptyList()
)

class HomeViewModel(
    private val repository: NexaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        loadHome()
    }

    fun loadHome() {
        viewModelScope.launch {
            val movies = repository.getTrendingMovies()
            val tv = repository.getTrendingTv()

            _state.value = HomeUiState(
                trendingMovies = movies,
                trendingTv = tv
            )
        }
    }
}
