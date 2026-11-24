package com.nexatv.app.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexatv.app.data.NexaRepository
import com.nexatv.app.data.model.MediaItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SeriesUiState(
    val series: List<MediaItem> = emptyList()
)

class SeriesViewModel(
    private val repository: NexaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SeriesUiState())
    val state: StateFlow<SeriesUiState> = _state

    init {
        loadSeries()
    }

    fun loadSeries() {
        viewModelScope.launch {
            val series = repository.getPopularTv()

            _state.value = SeriesUiState(
                series = series
            )
        }
    }
}
