package com.nexatv.app.ui.addons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nexatv.app.data.ApiKeys
import com.nexatv.app.data.StremioRepository

class AddonsViewModelFactory(
    private val repository: StremioRepository,
    private val apiKeys: ApiKeys
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddonsViewModel(repository, apiKeys) as T
    }
}
