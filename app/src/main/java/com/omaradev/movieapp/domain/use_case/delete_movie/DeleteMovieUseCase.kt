package com.omaradev.movieapp.domain.use_case.delete_movie

import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(
        imdbID: String
    ): Flow<Resource<*>> = flow {
        try {
            repository.deleteMovie(imdbID)
            emit(Resource.Success<Any>("Success"))
        } catch (e: Exception) {
            emit(Resource.Error<Any>(e.localizedMessage ?: "an Error Occurred"))
        }
    }
}