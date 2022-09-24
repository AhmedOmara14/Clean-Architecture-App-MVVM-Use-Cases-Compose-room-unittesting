package com.omaradev.movieapp.data.remote

import com.omaradev.movieapp.data.remote.dto.all_movies.AllMoviesDto
import com.omaradev.movieapp.data.remote.dto.movie_details.MovieDetailsAto
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET(".")
    suspend fun getMovies(
        @Query("s") movie: String,
        @Query("apikey") apikey: String,
        @Query("page") page: Int
    ): AllMoviesDto?

    @GET(".")
    suspend fun getMovieDetailsById(
        @Query("i") movie: String,
        @Query("apikey") apikey: String,
    ): MovieDetailsAto?
}