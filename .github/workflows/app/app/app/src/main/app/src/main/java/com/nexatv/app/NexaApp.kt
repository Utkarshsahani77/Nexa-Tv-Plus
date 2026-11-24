package com.nexatv.app

import android.app.Application
import com.nexatv.app.data.ApiKeys
import com.nexatv.app.data.LocalDatabase
import com.nexatv.app.data.NetworkModule
import com.nexatv.app.data.NexaRepository
import com.nexatv.app.data.StremioRepository

class NexaApp : Application() {

    val apiKeys by lazy { ApiKeys(this) }

    val database by lazy { LocalDatabase.get(this) }

    val repository: NexaRepository by lazy {
        NexaRepository(
            NetworkModule.createTmdbService(),
            NetworkModule.createOmdbService(),
            apiKeys
        )
    }

    val stremioRepository: StremioRepository by lazy {
        NetworkModule.createStremioRepository(apiKeys, database)
    }
}
