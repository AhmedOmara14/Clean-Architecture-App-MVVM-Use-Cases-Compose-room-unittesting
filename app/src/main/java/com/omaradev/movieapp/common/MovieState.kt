package com.omaradev.movieapp.common


data class MovieState(
    val isLoading: Boolean = false,
    val error: String = ""
)