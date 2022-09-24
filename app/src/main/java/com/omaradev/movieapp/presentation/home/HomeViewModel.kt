package com.omaradev.movieapp.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.movieapp.common.Constants
import com.omaradev.movieapp.common.Resource
import javax.inject.Inject
import com.omaradev.movieapp.domain.use_case.get_all_movies.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class HomeViewModel @Inject constructor(val getAllMoviesUseCase: GetAllMoviesUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(ListMoviesLastAddedState())
    val listMoviesLastAddedState: State<ListMoviesLastAddedState> = _state

    private val _listMoviesByCategoryState = mutableStateOf(ListMoviesLastAddedState())
    val listMoviesByCategoryState: State<ListMoviesLastAddedState> = _listMoviesByCategoryState

    val selectedCategory: MutableState<MovieCategory?> = mutableStateOf(null)

    init {
        getListMoviesAdded()
        getListByCategory(Constants.MOVIE_TYPE, Constants.API_TOKEN, 1)
        onSelectedCategoryChanged(Constants.MOVIE_TYPE)

    }

    private fun getListMoviesAdded() {
        getAllMoviesUseCase("Adventure", Constants.API_TOKEN, 1).onEach { response ->
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

    fun getListByCategory(
        movie: String,
        apikey: String,
        page: Int
    ) {
        getAllMoviesUseCase(movie, apikey, page).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _listMoviesByCategoryState.value = ListMoviesLastAddedState(isLoading = true)
                }
                is Resource.Success -> {
                    _listMoviesByCategoryState.value = ListMoviesLastAddedState(
                        allMoviesResponse = response.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _listMoviesByCategoryState.value =
                        ListMoviesLastAddedState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onSelectedCategoryChanged(category: String?) {
        val newCategory = getCategory(category)
        selectedCategory.value = newCategory
    }
}