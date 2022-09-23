package com.omaradev.movieapp.domain.model.movie_details

data class MovieDetails(

    val Actors: String?,
    val Awards: String?,
    val Country: String?,
    val Director: String?,
    val Genre: String?,
    val Language: String?,
    val Metascore: String?,
    val Plot: String?,
    val Poster: String?,
    val Rated: String?,
    val Ratings: List<Rating>?,
    val Released: String?,
    val Response: String?,
    val Runtime: String?,
    val Title: String?,
    val Type: String?,
    val Writer: String?,
    val Year: String?,
    val imdbID: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val totalSeasons: String?,
    val Error: String?
    )