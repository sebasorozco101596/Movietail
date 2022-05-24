package com.sebasorozcob.www.movietail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasorozcob.www.domain.common.Resource
import com.sebasorozcob.www.domain.use_case.movies.GetNowPlayingMoviesUseCase
import com.sebasorozcob.www.movietail.model.MoviesListState
import com.sebasorozcob.www.movietail.model.NowPlayingMoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
): ViewModel(){

    private val _state = MutableLiveData<NowPlayingMoviesState>()
    val state: LiveData<NowPlayingMoviesState> = _state

    private val _page = MutableLiveData<Int>()

    init {
        _page.value = 2
    }

    fun getMovies() {

        getNowPlayingMoviesUseCase(_page.value!!).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    //Log.println(Log.ASSERT,"DATA",result.data?.results.toString())
                    _state.value = NowPlayingMoviesState(movies = result.data)
                }
                is Resource.Error -> {
                    _state.value = NowPlayingMoviesState(error = result.message ?: "An unexpected error occurred!")
                }
                is Resource.Loading -> {
                    _state.value = NowPlayingMoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun moveRight() {
        _page.value = _page.value?.plus(1)
    }

    fun restartPage() {
        _page.value = 1
    }
}