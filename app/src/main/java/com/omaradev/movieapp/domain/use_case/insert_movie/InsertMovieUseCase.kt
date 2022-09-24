package com.omaradev.movieapp.domain.use_case.insert_movie

import android.util.Log
import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.data.remote.dto.movie_details.toDetails
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.model.movie_details.MovieDetails
import com.omaradev.movieapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(movie: Movie){
        repository.insertMovie(movie = movie)
    }
}