package com.omaradev.movieapp.domain.model.all_movies

import com.google.gson.annotations.SerializedName

data class AllMoviesResponse(
    val Response: String?,
    @SerializedName("Search")
    var movies: List<Movie>?
)