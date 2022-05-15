package com.sebasorozcob.www.domain.repository

import com.sebasorozcob.www.domain.model.Movies

interface MoviesRepository {
    suspend fun getMovies(): Movies
}