package com.sebasorozcob.www.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDto (
    @SerializedName("adult") val isAdult: Boolean,
    @SerializedName("backdrop_path") val posterImage: String,
    @SerializedName("genre_ids") val genreIds: ArrayList<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val mainImage: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)