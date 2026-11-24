package com.nexatv.app.data.remote

import com.nexatv.app.data.model.TmdbMovie
import com.nexatv.app.data.model.TmdbResponse
import com.nexatv.app.data.model.TmdbTv
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String
    ): TmdbResponse<TmdbMovie>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): TmdbResponse<TmdbMovie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): TmdbResponse<TmdbMovie>

    @GET("trending/tv/week")
    suspend fun getTrendingTv(
        @Query("api_key") apiKey: String
    ): TmdbResponse<TmdbTv>

    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") apiKey: String
    ): TmdbResponse<TmdbTv>

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("api_key") apiKey: String
    ): TmdbResponse<TmdbTv>

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TmdbResponse<TmdbSearchResult>
}
