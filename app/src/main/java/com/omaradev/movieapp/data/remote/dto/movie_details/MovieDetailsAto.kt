package com.omaradev.movieapp.data.remote.dto.movie_details

import com.omaradev.movieapp.domain.model.movie_details.MovieDetails
import com.omaradev.movieapp.domain.model.movie_details.Rating

data class MovieDetailsAto(
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

fun MovieDetailsAto.toDetails(): MovieDetails? {
    return MovieDetails(
        Actors,
        Awards,
        Country,
        Director,
        Genre,
        Language,
        Metascore,
        Plot,
        Poster,
        Rated,
        Ratings,
        Released,
        Response,
        Runtime,
        Title,
        Type,
        Writer,
        Year,
        imdbID,
        imdbRating,
        imdbVotes,
        totalSeasons,
        Error
    )
}