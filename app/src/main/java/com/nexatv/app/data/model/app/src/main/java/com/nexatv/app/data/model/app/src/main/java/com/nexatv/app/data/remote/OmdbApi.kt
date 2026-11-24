package com.nexatv.app.data.remote

import com.nexatv.app.data.model.OmdbResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/")
    suspend fun getMovie(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String
    ): OmdbResponse
}
