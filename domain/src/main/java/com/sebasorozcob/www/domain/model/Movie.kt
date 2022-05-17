package com.sebasorozcob.www.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val isAdult: Boolean,
    val posterImage: String?,
    val genreIds: ArrayList<Int>,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double?,
    val mainImage: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
): Parcelable