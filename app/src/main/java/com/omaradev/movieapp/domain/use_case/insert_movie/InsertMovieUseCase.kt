package com.omaradev.movieapp.domain.use_case.insert_movie

import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(
        movie: Movie
    ): Flow<Resource<*>> = flow {
        try {
            emit(Resource.Loading<Any>())
            val movies = repository.insertMovie(movie)
            emit(Resource.Success<Any>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<Any>(e.localizedMessage ?: "an Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Any>("No Internet Connection, Check your Internet"))
        }
    }
}