package com.sebasorozcob.www.movietail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasorozcob.www.domain.common.Resource
import com.sebasorozcob.www.domain.use_case.GetMoviesUseCase
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

    init {
        getMovies()
    }

    private fun getMovies() {
        getMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
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

}