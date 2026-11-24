package com.nexatv.app.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nexatv.app.data.NexaRepository

class SeriesViewModelFactory(
    private val repository: NexaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SeriesViewModel(repository) as T
    }
}
