package com.sebasorozcob.www.domain.repository

import com.sebasorozcob.www.domain.model.Movies

interface MoviesRepository {
    suspend fun getMovies(page: Int): Movies
    suspend fun searchMovies(query: String): Movies
}