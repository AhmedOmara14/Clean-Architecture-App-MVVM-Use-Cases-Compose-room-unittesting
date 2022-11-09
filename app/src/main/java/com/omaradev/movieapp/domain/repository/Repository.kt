package com.omaradev.movieapp.domain.repository

import com.omaradev.movieapp.domain.model.all_movies.AllMoviesResponse
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.model.movie_details.MovieDetails


interface Repository {
    suspend fun getMovies(
        movie: String, apikey: String, page: Int
    ): AllMoviesResponse?

    suspend fun getMovieDetailsById(imdbID: String, apikey: String): MovieDetails?

    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movieId: String)
    suspend fun getDownloads(): List<Movie>?

}