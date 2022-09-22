package com.omaradev.movieapp.domain.model.all_movies

import com.google.gson.annotations.SerializedName

data class AllMoviesResponse(
    @SerializedName("Search")
    val movies: ArrayList<Movie>
)