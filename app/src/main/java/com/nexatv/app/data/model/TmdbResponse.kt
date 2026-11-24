package com.nexatv.app.data.model

data class TmdbResponse<T>(
    val results: List<T>
)
