package com.omaradev.movieapp.presentation.download

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.use_case.delete_movie.DeleteMovieUseCase
import com.omaradev.movieapp.domain.use_case.get_all_movies.GetAllMoviesUseCase
import com.omaradev.movieapp.domain.use_case.get_movie_local.GetLocalMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DownloadViewModel
@Inject constructor(
    val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    val deleteMovieUseCase: DeleteMovieUseCase
) :
    ViewModel() {
    private val _state = mutableStateOf(DownloadMoviesState())
    val allMoviesState: State<DownloadMoviesState> = _state

    init {
        getLocalMovie()
    }

     fun getLocalMovie() {
        getLocalMoviesUseCase().onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _state.value = DownloadMoviesState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = DownloadMoviesState(
                        allMovies = response.data as ArrayList<Movie>?,
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

    public fun deleteMovie(movieId: String) {
        deleteMovieUseCase(movieId)
    }

}