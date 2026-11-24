package com.nexatv.app.data.model

data class TmdbTv(
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double
)
