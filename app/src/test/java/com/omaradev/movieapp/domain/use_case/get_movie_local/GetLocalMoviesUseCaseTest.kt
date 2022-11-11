package com.omaradev.movieapp.domain.use_case.get_movie_local

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.omaradev.movieapp.data.repository.FakeRepository
import com.omaradev.movieapp.domain.model.all_movies.Movie
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetLocalMoviesUseCaseTest {
    private lateinit var getLocalMoviesUseCase: GetLocalMoviesUseCase
    private lateinit var fakeRepository: FakeRepository
    private val moviesToInsert = mutableListOf<Movie>()

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getLocalMoviesUseCase = GetLocalMoviesUseCase(fakeRepository)

        moviesToInsert.add(Movie(1, "", "Movie1", "Action", "1990", "1"))
        moviesToInsert.add(Movie(2, "", "Movie2", "Action", "1990", "2"))
        moviesToInsert.add(Movie(3, "", "Movie3", "Action", "1990", "3"))
        runBlocking { moviesToInsert.forEach { fakeRepository.insertMovie(it) } }
    }

    @Test
    fun getLocalMovies() = runBlocking {
        getLocalMoviesUseCase.invoke().test {
            assertThat(awaitItem().data).isEqualTo(moviesToInsert)
            cancelAndConsumeRemainingEvents()
        }
    }
}