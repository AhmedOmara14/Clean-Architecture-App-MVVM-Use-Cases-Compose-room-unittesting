package com.omaradev.movieapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.movieapp.common.Constants
import com.omaradev.movieapp.common.Resource
import javax.inject.Inject
import com.omaradev.movieapp.domain.use_case.get_all_movies.GetAllMoviesLastAddedUseCase
import com.omaradev.movieapp.presentation.home.ListMoviesLastAddedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class HomeViewModel @Inject constructor(val getAllMoviesLastAddedUseCase: GetAllMoviesLastAddedUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(ListMoviesLastAddedState())
    val listMoviesLastAddedState: State<ListMoviesLastAddedState> = _state


    init {
        getListMoviesAdded(Constants.MOVIE_TYPE,Constants.API_TOKEN,1)
    }

    private fun getListMoviesAdded(
        movie: String,
        apikey: String,
        page: Int
    ) {
        getAllMoviesLastAddedUseCase(movie, apikey, page).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _state.value = ListMoviesLastAddedState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ListMoviesLastAddedState(
                        allMoviesResponse = response.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        ListMoviesLastAddedState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }
}