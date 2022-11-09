package com.omaradev.movieapp.domain.use_case.get_movie_local

import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLocalMoviesUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(
    ): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading<List<Movie>>())
            val movies = repository.getDownloads()
            emit(Resource.Success<List<Movie>>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Movie>>(e.localizedMessage ?: "an Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Movie>>("No Internet Connection, Check your Internet"))
        }
    }
}