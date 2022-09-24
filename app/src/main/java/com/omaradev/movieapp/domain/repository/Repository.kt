package com.omaradev.movieapp.domain.repository

import com.omaradev.movieapp.data.remote.dto.all_movies.AllMoviesDto
import com.omaradev.movieapp.data.remote.dto.movie_details.MovieDetailsAto


interface Repository {
    suspend fun getMovies(
        movie: String, apikey: String, page: Int
    ): AllMoviesDto?

    suspend fun getMovieDetailsById(imdbID:String ,apikey: String) :MovieDetailsAto?
}