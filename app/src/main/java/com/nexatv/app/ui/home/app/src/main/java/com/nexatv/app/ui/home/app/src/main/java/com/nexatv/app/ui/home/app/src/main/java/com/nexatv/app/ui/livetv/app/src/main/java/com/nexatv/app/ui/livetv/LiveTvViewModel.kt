package com.nexatv.app.ui.livetv

import androidx.lifecycle.ViewModel
import com.nexatv.app.data.model.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class LiveTvUiState(
    val channels: List<Channel> = emptyList()
)

class LiveTvViewModel : ViewModel() {

    private val _state = MutableStateFlow(LiveTvUiState())
    val state: StateFlow<LiveTvUiState> = _state

    init {
        loadChannels()
    }

    fun loadChannels() {
        val channels = listOf(
            Channel(
                name = "Demo Channel",
                url = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8"
            )
        )

        _state.value = LiveTvUiState(
            channels = channels
        )
    }
}
