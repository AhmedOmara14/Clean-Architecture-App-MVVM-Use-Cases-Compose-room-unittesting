package com.omaradev.movieapp.presentation.search

import com.omaradev.movieapp.domain.model.all_movies.AllMoviesResponse
import com.omaradev.movieapp.domain.model.all_movies.Movie

data class SearchMoviesState(
    val isLoading: Boolean = false,
    var allMoviesResponse: List<Movie>? = null,
    val error: String = ""
)