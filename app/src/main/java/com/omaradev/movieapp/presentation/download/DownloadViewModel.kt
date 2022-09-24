package com.omaradev.movieapp.presentation.download

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.use_case.get_movie_local.GetLocalMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DownloadViewModel
@Inject constructor(
    val getLocalMoviesUseCase: GetLocalMoviesUseCase
) :
    ViewModel() {
    private val _state = mutableStateOf(DownloadMoviesState())
    val allMoviesState: State<DownloadMoviesState> = _state
   init {
       getLocalMovie()
   }
    private fun getLocalMovie() {
        getLocalMoviesUseCase().onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _state.value = DownloadMoviesState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = DownloadMoviesState(
                        allMovies = response.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        DownloadMoviesState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

}