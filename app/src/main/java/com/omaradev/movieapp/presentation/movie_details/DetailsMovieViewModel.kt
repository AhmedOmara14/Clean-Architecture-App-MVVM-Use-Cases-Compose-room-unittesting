package com.omaradev.movieapp.presentation.movie_details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.movieapp.common.Constants
import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.use_case.get_details_movie.GetDetailsMovieUseCase
import com.omaradev.movieapp.domain.use_case.get_movie_local.GetLocalMoviesUseCase
import com.omaradev.movieapp.domain.use_case.insert_movie.InsertMovieUseCase
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

    val imdbID: MutableState<String?> = mutableStateOf(null)

    init {
        savedStateHandle.get<String>(Constants.MOVIE_ID)?.let { movieId ->
            getDetailsMovieById(movieId, Constants.API_TOKEN)
        }
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
        insertMovieUseCase(movie)
    }
    fun getLocalMovie() {
        getLocalMoviesUseCase().onEach { response ->
            when (response) {
                is Resource.Success -> {
                    Log.d("TAG", "getLocalMovie: "+response.data?.size)
                }
            }
        }.launchIn(viewModelScope)
    }

}