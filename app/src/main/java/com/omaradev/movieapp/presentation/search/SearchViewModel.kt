package com.omaradev.movieapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.movieapp.common.Constants
import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.model.all_movies.AllMoviesResponse
import com.omaradev.movieapp.domain.model.all_movies.Movie
import javax.inject.Inject
import com.omaradev.movieapp.domain.use_case.get_all_movies.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(val getAllMoviesUseCase: GetAllMoviesUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(SearchMoviesState())
    val searchMoviesState: State<SearchMoviesState> = _state
    val page = mutableStateOf(1)
    var moviesListScrollPosition = 0
    private var result: Resource<AllMoviesResponse>? = null

    fun search(
        movie: String,
        apikey: String,
        page: Int
    ) {
        getAllMoviesUseCase(movie, apikey, page).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _state.value = SearchMoviesState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = SearchMoviesState(
                        allMoviesResponse = response.data?.movies,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value =
                        SearchMoviesState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun appendMovies(movies: List<Movie>) {
        val current = searchMoviesState.value.allMoviesResponse?.let { ArrayList(it) }
        current?.addAll(movies)
        _state.value = SearchMoviesState(
            allMoviesResponse = current,
            isLoading = false
        )
    }

    fun nextPage(movie: String) {
        viewModelScope.launch {
            if ((moviesListScrollPosition + 1) >= (page.value * Constants.PAGE_SIZE)) {
                incrementPage()
                if (page.value > 1) {
                    getAllMoviesUseCase(
                        movie,
                        Constants.API_TOKEN,
                        page.value
                    ).collect {
                        result = it
                    }
                    result?.data?.movies?.let { appendMovies(it) }
                }

            }
        }
    }

    private fun incrementPage() {
        page.value += 1
    }
    fun onChangeMoviesListScrollPosition(position: Int) {
        moviesListScrollPosition = position
    }
}