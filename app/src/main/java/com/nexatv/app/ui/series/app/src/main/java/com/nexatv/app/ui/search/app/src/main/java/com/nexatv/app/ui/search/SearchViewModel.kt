package com.nexatv.app.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexatv.app.data.NexaRepository
import com.nexatv.app.data.model.MediaItem
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

data class SearchUiState(
    val query: String = "",
    val results: List<MediaItem> = emptyList()
)

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val repository: NexaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state

    fun onQueryChange(query: String) {
        _state.value = _state.value.copy(
            query = query
        )

        viewModelScope.launch {
            kotlinx.coroutines.flow.MutableStateFlow(query)
                .debounce(300)
                .collect {
                    if (it.length > 2) {
                        val results = repository.searchMulti(it)

                        _state.value = _state.value.copy(
                            results = results
                        )
                    }
                }
        }
    }
}
