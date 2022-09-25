package com.omaradev.movieapp.presentation.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.use_case.get_all_movies.GetAllMoviesUseCase
import com.omaradev.movieapp.domain.use_case.get_movie_local.GetLocalMoviesUseCase
import com.omaradev.movieapp.presentation.home.ListMoviesLastAddedState
import com.omaradev.movieapp.presentation.movie_details.DetailsMovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(val getLocalMoviesUseCase: GetLocalMoviesUseCase) :ViewModel(){
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getLocalMovie()
    }
    fun getLocalMovie() {
        getLocalMoviesUseCase().onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _state.value = MainState(
                        numberOfDownloads = response.data?.size.toString(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}