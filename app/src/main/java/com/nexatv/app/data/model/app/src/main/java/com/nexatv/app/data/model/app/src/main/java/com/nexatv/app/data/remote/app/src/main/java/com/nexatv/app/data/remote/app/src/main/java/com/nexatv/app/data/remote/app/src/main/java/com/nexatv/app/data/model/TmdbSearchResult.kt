package com.nexatv.app.data.model

data class TmdbSearchResult(
    val id: Int,
    val media_type: String?,
    val title: String?,
    val name: String?,
    val overview: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double
)
