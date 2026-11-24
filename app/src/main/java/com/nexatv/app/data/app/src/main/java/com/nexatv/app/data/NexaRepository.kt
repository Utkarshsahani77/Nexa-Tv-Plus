package com.nexatv.app.data

import com.nexatv.app.data.model.MediaItem
import com.nexatv.app.data.model.TmdbMovie
import com.nexatv.app.data.model.TmdbTv
import com.nexatv.app.data.remote.OmdbApi
import com.nexatv.app.data.remote.TmdbApi
import kotlinx.coroutines.flow.firstOrNull

class NexaRepository(
    private val tmdbApi: TmdbApi,
    private val omdbApi: OmdbApi,
    private val apiKeys: ApiKeys
) {

    suspend fun getTrendingMovies(): List<MediaItem> {
        val key = apiKeys.tmdbApiKey.firstOrNull() ?: return emptyList()

        return try {
            val response = tmdbApi.getTrendingMovies(key)

            response.results.map { movie ->
                MediaItem(
                    id = movie.id.toString(),
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = movie.poster_path,
                    backdropPath = movie.backdrop_path,
                    rating = movie.vote_average,
                    type = "movie"
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPopularMovies(): List<MediaItem> {
        val key = apiKeys.tmdbApiKey.firstOrNull() ?: return emptyList()

        return try {
            val response = tmdbApi.getPopularMovies(key)

            response.results.map { movie ->
                MediaItem(
                    id = movie.id.toString(),
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = movie.poster_path,
                    backdropPath = movie.backdrop_path,
                    rating = movie.vote_average,
                    type = "movie"
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getTopRatedMovies(): List<MediaItem> {
        val key = apiKeys.tmdbApiKey.firstOrNull() ?: return emptyList()

        return try {
            val response = tmdbApi.getTopRatedMovies(key)

            response.results.map { movie ->
                MediaItem(
                    id = movie.id.toString(),
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = movie.poster_path,
                    backdropPath = movie.backdrop_path,
                    rating = movie.vote_average,
                    type = "movie"
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getTrendingTv(): List<MediaItem> {
        val key = apiKeys.tmdbApiKey.firstOrNull() ?: return emptyList()

        return try {
            val response = tmdbApi.getTrendingTv(key)

            response.results.map { tv ->
                MediaItem(
                    id = tv.id.toString(),
                    title = tv.name,
                    overview = tv.overview,
                    posterPath = tv.poster_path,
                    backdropPath = tv.backdrop_path,
                    rating = tv.vote_average,
                    type = "series"
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPopularTv(): List<MediaItem> {
        val key = apiKeys.tmdbApiKey.firstOrNull() ?: return emptyList()

        return try {
            val response = tmdbApi.getPopularTv(key)

            response.results.map { tv ->
                MediaItem(
                    id = tv.id.toString(),
                    title = tv.name,
                    overview = tv.overview,
                    posterPath = tv.poster_path,
                    backdropPath = tv.backdrop_path,
                    rating = tv.vote_average,
                    type = "series"
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getTopRatedTv(): List<MediaItem> {
        val key = apiKeys.tmdbApiKey.firstOrNull() ?: return emptyList()

        return try {
            val response = tmdbApi.getTopRatedTv(key)

            response.results.map { tv ->
                MediaItem(
                    id = tv.id.toString(),
                    title = tv.name,
                    overview = tv.overview,
                    posterPath = tv.poster_path,
                    backdropPath = tv.backdrop_path,
                    rating = tv.vote_average,
                    type = "series"
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchMulti(query: String): List<MediaItem> {
        val key = apiKeys.tmdbApiKey.firstOrNull() ?: return emptyList()

        return try {
            val response = tmdbApi.searchMulti(key, query)

            response.results.mapNotNull { result ->
                when {
                    result.media_type == "movie" -> {
                        MediaItem(
                            id = result.id.toString(),
                            title = result.title ?: "",
                            overview = result.overview ?: "",
                            posterPath = result.poster_path,
                            backdropPath = result.backdrop_path,
                            rating = result.vote_average,
                            type = "movie"
                        )
                    }

                    result.media_type == "tv" -> {
                        MediaItem(
                            id = result.id.toString(),
                            title = result.name ?: "",
                            overview = result.overview ?: "",
                            posterPath = result.poster_path,
                            backdropPath = result.backdrop_path,
                            rating = result.vote_average,
                            type = "series"
                        )
                    }

                    else -> null
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
