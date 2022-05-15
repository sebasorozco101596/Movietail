package com.sebasorozcob.www.data.repository

import com.sebasorozcob.www.data.remote.api.MovietailApi
import com.sebasorozcob.www.data.remote.extensions.toMovies
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovietailApi
): MoviesRepository {

    override suspend fun getMovies(): Movies {
        return api.getMovies(4).body()!!.toMovies()
    }

}