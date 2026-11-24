package com.nexatv.app.data.model

data class TmdbMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double
)
