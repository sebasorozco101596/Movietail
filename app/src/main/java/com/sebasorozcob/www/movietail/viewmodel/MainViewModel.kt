package com.sebasorozcob.www.movietail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasorozcob.www.domain.common.Resource
import com.sebasorozcob.www.domain.use_case.movies.GetMoviesUseCase
import com.sebasorozcob.www.movietail.model.MoviesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    private val _state = MutableLiveData<MoviesListState>()
    val state: LiveData<MoviesListState> = _state

    private val _page = MutableLiveData<Int>()

    init {
        _page.value = 1
        //getMovies()
    }

    fun getMovies() {

        getMoviesUseCase(_page.value!!).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    //Log.println(Log.ASSERT,"DATA",result.data?.results.toString())
                    _state.value = MoviesListState(movies = result.data)
                }
                is Resource.Error -> {
                    _state.value = MoviesListState(error = result.message ?: "An unexpected error occurred!")
                }
                is Resource.Loading -> {
                    _state.value = MoviesListState(isLoading = true)
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