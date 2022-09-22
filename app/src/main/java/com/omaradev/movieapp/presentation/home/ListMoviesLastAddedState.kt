package com.omaradev.movieapp.presentation.home

import com.omaradev.movieapp.domain.model.all_movies.AllMoviesResponse

data class ListMoviesLastAddedState(
    val isLoading: Boolean = false,
    val allMoviesResponse: AllMoviesResponse? = null,
    val error: String = ""
)