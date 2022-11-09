package com.omaradev.movieapp.domain.use_case.get_all_movies

import com.omaradev.movieapp.common.Resource
import com.omaradev.movieapp.domain.model.all_movies.AllMoviesResponse
import com.omaradev.movieapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(
        movie: String,
        apikey: String,
        page: Int
    ): Flow<Resource<AllMoviesResponse>> = flow {
        try {
            emit(Resource.Loading<AllMoviesResponse>())
            val movies = repository.getMovies(movie, apikey, page)
            emit(Resource.Success<AllMoviesResponse>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<AllMoviesResponse>(e.localizedMessage ?: "an Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<AllMoviesResponse>("No Internet Connection, Check your Internet"))
        }
    }
}