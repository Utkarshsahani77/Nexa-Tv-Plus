package com.nexatv.app.ui.stalker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nexatv.app.data.NexaRepository

class StalkerViewModelFactory(
    private val repository: NexaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StalkerViewModel() as T
    }
}
