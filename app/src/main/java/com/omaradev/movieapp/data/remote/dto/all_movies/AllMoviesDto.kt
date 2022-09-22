package com.omaradev.movieapp.data.remote.dto.all_movies

import com.google.gson.annotations.SerializedName
import com.omaradev.movieapp.domain.model.all_movies.AllMoviesResponse
import com.omaradev.movieapp.domain.model.all_movies.Movie

data class AllMoviesDto(
    @SerializedName("Search")
    val movies: ArrayList<Movie>,
)

fun AllMoviesDto.toMovie() :AllMoviesResponse{
    return AllMoviesResponse(
        movies
    )

}
