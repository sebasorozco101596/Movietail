package com.sebasorozcob.www.domain.use_case.movies

import com.sebasorozcob.www.domain.common.Resource
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.domain.model.NowPlayingMovies
import com.sebasorozcob.www.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
){
    operator fun invoke(page: Int): Flow<Resource<NowPlayingMovies>> = flow {
        try {
            emit(Resource.Loading())
            val nowPLayingMovies = repository.getNowPlayingMovies(page)
            emit(Resource.Success(nowPLayingMovies))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}