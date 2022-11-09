package com.omaradev.movieapp.data.remote

import com.omaradev.movieapp.domain.model.all_movies.AllMoviesResponse
import com.omaradev.movieapp.domain.model.movie_details.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET(".")
    suspend fun getMovies(
        @Query("s") movie: String,
        @Query("apikey") apikey: String,
        @Query("page") page: Int
    ): AllMoviesResponse?

    @GET(".")
    suspend fun getMovieDetailsById(
        @Query("i") movie: String,
        @Query("apikey") apikey: String,
    ): MovieDetails?
}