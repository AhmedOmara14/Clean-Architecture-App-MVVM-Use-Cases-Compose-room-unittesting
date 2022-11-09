package com.omaradev.movieapp.presentation.movie_details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.movieapp.common.Constants
import com.omaradev.movieapp.common.MovieState
import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.use_case.get_details_movie.GetDetailsMovieUseCase
import com.omaradev.movieapp.domain.use_case.get_movie_local.GetLocalMoviesUseCase
import com.omaradev.movieapp.domain.use_case.insert_movie.InsertMovieUseCase
import com.omaradev.movieapp.presentation.download.DownloadMoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsMovieViewModel
@Inject constructor(
    val getDetailsMovieUseCase: GetDetailsMovieUseCase,
    savedStateHandle: SavedStateHandle,
    val insertMovieUseCase: InsertMovieUseCase,
    val getLocalMoviesUseCase: GetLocalMoviesUseCase
) :
    ViewModel() {
    private val _state = mutableStateOf(DetailsMovieState())
    val detailsMovieState: State<DetailsMovieState> = _state

    private val _allMoviesState = mutableStateOf(DownloadMoviesState())
    val allMoviesState: State<DownloadMoviesState> = _allMoviesState

    private val _addState = mutableStateOf(MovieState())
    val addState: State<MovieState> = _addState

    val imdbID: MutableState<String?> = mutableStateOf(null)

    init {
        savedStateHandle.get<String>(Constants.MOVIE_ID)?.let { movieId ->
            getDetailsMovieById(movieId, Constants.API_TOKEN)
        }
        getLocalMovie()
    }

    fun getDetailsMovieById(imdbID: String, apiKey: String) {
        getDetailsMovieUseCase(imdbID, apiKey).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _state.value = DetailsMovieState(
                        movieDetails = response.data
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        DetailsMovieState(error = response.message ?: "unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = DetailsMovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertMovie(movie: Movie) {
        insertMovieUseCase(movie).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _addState.value = MovieState(isLoading = false)
                }
                is Resource.Error -> {
                    _addState.value =
                        MovieState(error = response.message ?: "unexpected error")
                }
                is Resource.Loading -> {
                    _addState.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getLocalMovie() {
        getLocalMoviesUseCase().onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _allMoviesState.value = DownloadMoviesState(isLoading = true)
                }
                is Resource.Success -> {
                    _allMoviesState.value = DownloadMoviesState(
                        allMovies = response.data as ArrayList<Movie>?,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _allMoviesState.value =
                        DownloadMoviesState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

}