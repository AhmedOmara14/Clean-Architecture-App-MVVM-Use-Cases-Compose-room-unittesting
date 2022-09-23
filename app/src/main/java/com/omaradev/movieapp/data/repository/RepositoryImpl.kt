package com.omaradev.movieapp.data.repository

import com.omaradev.movieapp.data.remote.Api
import com.omaradev.movieapp.data.remote.dto.all_movies.AllMoviesDto
import com.omaradev.movieapp.data.remote.dto.movie_details.MovieDetailsAto
import com.omaradev.movieapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val api: Api) : Repository {
    override suspend fun getMovies(movie: String, apikey: String, page: Int): AllMoviesDto {
        return api.getMovies(movie, apikey, page)
    }

    override suspend fun getMovieDetailsById(imdbID: String, apikey: String): MovieDetailsAto? {
        return api.getMovieDetailsById(imdbID, apikey)
    }


}