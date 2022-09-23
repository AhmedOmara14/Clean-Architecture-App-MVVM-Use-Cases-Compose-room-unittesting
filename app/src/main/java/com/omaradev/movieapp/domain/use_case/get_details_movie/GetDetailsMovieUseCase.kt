package com.omaradev.movieapp.domain.use_case.get_details_movie

import android.util.Log
import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.data.remote.dto.movie_details.toDetails
import com.omaradev.movieapp.domain.model.movie_details.MovieDetails
import com.omaradev.movieapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailsMovieUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(imdbID: String, apiKey: String):
            Flow<Resource<MovieDetails>> = flow {
        try {
            emit(Resource.Loading<MovieDetails>())
            val details = repository.getMovieDetailsById(imdbID, apiKey)?.toDetails()
            emit(Resource.Success<MovieDetails>(details))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error<MovieDetails>(e.localizedMessage ?: "an Error Occurred"))
        } catch (e: java.io.IOException) {
            emit(Resource.Error<MovieDetails>("No Internet Connection, Check your Internet"))
        }

    }
}