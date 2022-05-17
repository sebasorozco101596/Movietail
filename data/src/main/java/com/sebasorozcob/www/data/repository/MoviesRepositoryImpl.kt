package com.sebasorozcob.www.data.repository

import com.sebasorozcob.www.data.mapper.toMovies
import com.sebasorozcob.www.data.remote.api.MovietailApi
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovietailApi
): MoviesRepository {

    override suspend fun getMovies(): Movies {
        return api.getMovies(1).body()!!.toMovies()
    }

    override suspend fun searchMovies(query: String): Movies {
        return if (query.isNotBlank()) {
            api.searchMovies(query).body()!!.toMovies()
        } else {
            api.getMovies(1).body()!!.toMovies()
        }
    }

}