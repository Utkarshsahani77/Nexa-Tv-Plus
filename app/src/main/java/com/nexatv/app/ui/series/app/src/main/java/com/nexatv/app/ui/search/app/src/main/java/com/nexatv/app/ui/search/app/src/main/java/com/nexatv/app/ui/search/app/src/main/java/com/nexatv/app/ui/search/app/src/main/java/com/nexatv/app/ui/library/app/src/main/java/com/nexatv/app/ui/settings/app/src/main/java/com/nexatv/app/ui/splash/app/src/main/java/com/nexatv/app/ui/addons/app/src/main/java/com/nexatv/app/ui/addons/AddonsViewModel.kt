package com.nexatv.app.ui.addons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexatv.app.data.ApiKeys
import com.nexatv.app.data.StremioRepository
import com.nexatv.app.data.model.Addon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AddonsUiState(
    val addonUrl: String = "",
    val addons: List<Addon> = emptyList()
)

class AddonsViewModel(
    private val repository: StremioRepository,
    private val apiKeys: ApiKeys
) : ViewModel() {

    private val _state = MutableStateFlow(AddonsUiState())
    val state: StateFlow<AddonsUiState> = _state

    init {
        loadAddons()
    }

    fun loadAddons() {
        viewModelScope.launch {
            val addons = repository.getAddons()

            _state.value = _state.value.copy(
                addons = addons
            )
        }
    }

    fun onAddonUrlChange(url: String) {
        _state.value = _state.value.copy(
            addonUrl = url
        )
    }

    fun addAddon() {
        viewModelScope.launch {
            val url = _state.value.addonUrl

            if (url.isNotBlank()) {
                repository.addAddon(url)
                loadAddons()
            }
        }
    }
}
