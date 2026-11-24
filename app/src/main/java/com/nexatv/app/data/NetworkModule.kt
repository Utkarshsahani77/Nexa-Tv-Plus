package com.nexatv.app.data

import com.nexatv.app.data.local.LocalDatabase
import com.nexatv.app.data.remote.OmdbApi
import com.nexatv.app.data.remote.StremioApi
import com.nexatv.app.data.remote.TmdbApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    private const val OMDB_BASE_URL = "https://www.omdbapi.com/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    private val retrofitTmdb = Retrofit.Builder()
        .baseUrl(TMDB_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitOmdb = Retrofit.Builder()
        .baseUrl(OMDB_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createTmdbService(): TmdbApi {
        return retrofitTmdb.create(TmdbApi::class.java)
    }

    fun createOmdbService(): OmdbApi {
        return retrofitOmdb.create(OmdbApi::class.java)
    }

    fun createStremioRepository(
        apiKeys: ApiKeys,
        database: LocalDatabase
    ): StremioRepository {
        return StremioRepository(apiKeys, database)
    }
}
