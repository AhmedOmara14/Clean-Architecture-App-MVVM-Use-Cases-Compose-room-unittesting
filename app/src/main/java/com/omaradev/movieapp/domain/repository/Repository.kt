package com.omaradev.movieapp.domain.repository

import androidx.lifecycle.LiveData
import com.omaradev.movieapp.data.remote.dto.all_movies.AllMoviesDto
import com.omaradev.movieapp.data.remote.dto.movie_details.MovieDetailsAto
import com.omaradev.movieapp.domain.model.all_movies.Movie


interface Repository {
    suspend fun getMovies(
        movie: String, apikey: String, page: Int
    ): AllMoviesDto?

    suspend fun getMovieDetailsById(imdbID: String, apikey: String): MovieDetailsAto?

    fun insertMovie(movie: Movie)
    fun deleteMovie(movieId: String)
    fun getDownloads(): List<Movie>?

}