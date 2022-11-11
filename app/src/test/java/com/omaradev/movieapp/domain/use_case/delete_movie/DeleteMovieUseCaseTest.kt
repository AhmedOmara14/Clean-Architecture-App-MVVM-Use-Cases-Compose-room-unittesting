package com.omaradev.movieapp.domain.use_case.delete_movie

import com.omaradev.movieapp.data.repository.FakeRepository
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.use_case.get_movie_local.GetLocalMoviesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before

class DeleteMovieUseCaseTest {
    private lateinit var fakeRepository: FakeRepository
    private val moviesToInsert = mutableListOf<Movie>()

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()

        moviesToInsert.add(Movie(1, "", "Movie1", "Action", "1990", "1"))
        runBlocking { moviesToInsert.forEach { fakeRepository.insertMovie(it) } }
    }

}