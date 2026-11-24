package com.nexatv.app.data.model

data class MediaItem(
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val rating: Double,
    val type: String
)
