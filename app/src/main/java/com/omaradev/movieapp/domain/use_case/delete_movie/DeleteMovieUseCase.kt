package com.omaradev.movieapp.domain.use_case.delete_movie

import com.omaradev.movieapp.domain.repository.Repository
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(imdbID: String){
        repository.deleteMovie(movieId = imdbID)
    }
}