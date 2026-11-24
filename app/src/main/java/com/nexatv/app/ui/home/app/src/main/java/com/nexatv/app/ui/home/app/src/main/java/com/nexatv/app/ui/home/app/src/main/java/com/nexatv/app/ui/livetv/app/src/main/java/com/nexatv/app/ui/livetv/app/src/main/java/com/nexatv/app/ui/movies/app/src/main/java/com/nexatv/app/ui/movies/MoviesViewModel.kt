package com.nexatv.app.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexatv.app.data.NexaRepository
import com.nexatv.app.data.model.MediaItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MoviesUiState(
    val movies: List<MediaItem> = emptyList()
)

class MoviesViewModel(
    private val repository: NexaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesUiState())
    val state: StateFlow<MoviesUiState> = _state

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            val movies = repository.getPopularMovies()

            _state.value = MoviesUiState(
                movies = movies
            )
        }
    }
}
