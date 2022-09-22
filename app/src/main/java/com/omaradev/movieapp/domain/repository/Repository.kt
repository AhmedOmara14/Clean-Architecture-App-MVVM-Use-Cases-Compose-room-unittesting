package com.omaradev.movieapp.domain.repository

import com.omaradev.movieapp.data.remote.dto.all_movies.AllMoviesDto


interface Repository {
    suspend fun getMovies(
        movie: String, apikey: String, page: Int
    ): AllMoviesDto
}