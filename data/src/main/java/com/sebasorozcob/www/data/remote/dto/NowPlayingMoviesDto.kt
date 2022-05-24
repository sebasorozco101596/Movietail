package com.sebasorozcob.www.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NowPlayingMoviesDto(
    @SerializedName("dates") val dates: DatesDto,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
