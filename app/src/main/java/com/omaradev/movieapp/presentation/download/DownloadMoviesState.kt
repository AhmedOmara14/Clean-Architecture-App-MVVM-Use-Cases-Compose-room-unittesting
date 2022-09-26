package com.omaradev.movieapp.presentation.download

import com.omaradev.movieapp.domain.model.all_movies.Movie

data class DownloadMoviesState(
    val isLoading: Boolean = false,
    val allMovies: ArrayList<Movie>? = null,
    val error: String = ""
)