package com.sebasorozcob.www.domain.model

import com.google.gson.annotations.SerializedName

data class NowPlayingMovies(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
