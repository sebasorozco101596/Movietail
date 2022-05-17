package com.sebasorozcob.www.movietail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasorozcob.www.domain.common.Resource
import com.sebasorozcob.www.domain.use_case.credits.GetCreditsUseCase
import com.sebasorozcob.www.movietail.model.CreditsListState
import com.sebasorozcob.www.movietail.model.MoviesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreditsViewModel @Inject constructor(
    val creditsUseCase: GetCreditsUseCase
): ViewModel(){

    private val _state = MutableLiveData<CreditsListState>()
    val state: LiveData<CreditsListState> = _state

    fun getCredits(movieId: String) {
        creditsUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CreditsListState(credits = result.data)
                }
                is Resource.Error -> {
                    _state.value = CreditsListState(error = result.message ?: "An unexpected error occurred!")
                }
                is Resource.Loading -> {
                    _state.value = CreditsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}