package com.omaradev.movieapp.presentation.main

data class MainState(
    val isLoading: Boolean = false,
    val numberOfDownloads: String? = null,
    val error: String = ""
)