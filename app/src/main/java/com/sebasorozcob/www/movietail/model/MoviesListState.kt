package com.sebasorozcob.www.movietail.model

import com.sebasorozcob.www.domain.model.Movies

data class MoviesListState(
    val isLoading: Boolean = false,
    val movies: Movies? = null,
    val error: String = ""
)