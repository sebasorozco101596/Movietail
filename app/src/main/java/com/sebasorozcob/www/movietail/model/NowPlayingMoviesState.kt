package com.sebasorozcob.www.movietail.model

import com.sebasorozcob.www.domain.model.NowPlayingMovies

data class NowPlayingMoviesState(
    val isLoading: Boolean = false,
    val movies: NowPlayingMovies? = null,
    val error: String = ""
)