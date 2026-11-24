package com.nexatv.app.ui.stalker

import androidx.lifecycle.ViewModel
import com.nexatv.app.data.model.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class StalkerUiState(
    val portalUrl: String = "",
    val channels: List<Channel> = emptyList()
)

class StalkerViewModel : ViewModel() {

    private val _state = MutableStateFlow(StalkerUiState())
    val state: StateFlow<StalkerUiState> = _state

    fun onPortalUrlChange(url: String) {
        _state.value = _state.value.copy(
            portalUrl = url
        )
    }

    fun loadChannels() {
        val channels = listOf(
            Channel(
                name = "Demo Channel",
                url = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8"
            )
        )

        _state.value = _state.value.copy(
            channels = channels
        )
    }
}
