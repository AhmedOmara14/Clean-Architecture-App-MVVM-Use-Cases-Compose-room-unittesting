package com.omaradev.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.omaradev.movieapp.data.local.MovieDB
import com.omaradev.movieapp.data.remote.Api
import com.omaradev.movieapp.data.remote.dto.all_movies.AllMoviesDto
import com.omaradev.movieapp.data.remote.dto.movie_details.MovieDetailsAto
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val api: Api,private val db: MovieDB) : Repository {
    override suspend fun getMovies(movie: String, apikey: String, page: Int): AllMoviesDto? {
        return api.getMovies(movie, apikey, page)
    }

    override suspend fun getMovieDetailsById(imdbID: String, apikey: String): MovieDetailsAto? {
        return api.getMovieDetailsById(imdbID, apikey)
    }

    override suspend fun insertMovie(movie: Movie) {
        db.movieDao.insert(movie)
    }

    override suspend fun deleteMovie(movieId: String) {
        db.movieDao.deleteMovie(movieId)
    }
    override suspend fun getDownloads(): List<Movie> {
        return db.movieDao.getMovies()
    }


}