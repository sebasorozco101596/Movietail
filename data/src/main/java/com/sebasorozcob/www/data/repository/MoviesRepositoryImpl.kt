package com.sebasorozcob.www.data.repository

import com.sebasorozcob.www.data.mapper.toMovies
import com.sebasorozcob.www.data.mapper.toNowPlayingMovies
import com.sebasorozcob.www.data.remote.api.MovietailApi
import com.sebasorozcob.www.domain.model.Dates
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.domain.model.NowPlayingMovies
import com.sebasorozcob.www.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovietailApi
): MoviesRepository {

    override suspend fun getMovies(page: Int): Movies {
        return api.getMovies(page).body()?.toMovies() ?: Movies(0, emptyList(),0,0)
    }

    override suspend fun searchMovies(query: String): Movies {
        return if (query.isNotBlank()) {
            api.searchMovies(query).body()!!.toMovies()
        } else {
            api.getMovies(1).body()!!.toMovies()
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): NowPlayingMovies {
        return api.getMoviesNowPlaying(page).body()?.toNowPlayingMovies() ?: NowPlayingMovies(
            Dates("",""), 0, emptyList(), 0,0)

    }

}