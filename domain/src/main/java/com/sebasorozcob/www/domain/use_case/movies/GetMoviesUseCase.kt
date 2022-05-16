package com.sebasorozcob.www.domain.use_case.movies

import com.sebasorozcob.www.domain.common.Resource
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
){

    operator fun invoke(): Flow<Resource<Movies>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getMovies()
            emit(Resource.Success(movies))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}