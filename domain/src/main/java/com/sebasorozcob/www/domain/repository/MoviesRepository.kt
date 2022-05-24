package com.sebasorozcob.www.domain.repository

import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.domain.model.NowPlayingMovies

interface MoviesRepository {
    suspend fun getMovies(page: Int): Movies
    suspend fun searchMovies(query: String): Movies
    suspend fun getNowPlayingMovies(page: Int): NowPlayingMovies
}