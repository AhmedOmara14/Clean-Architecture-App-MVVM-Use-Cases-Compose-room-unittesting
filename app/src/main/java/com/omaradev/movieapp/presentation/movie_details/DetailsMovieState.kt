package com.omaradev.movieapp.presentation.movie_details

import com.omaradev.movieapp.domain.model.movie_details.MovieDetails

data class DetailsMovieState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetails? = null,
    val error: String = ""
)