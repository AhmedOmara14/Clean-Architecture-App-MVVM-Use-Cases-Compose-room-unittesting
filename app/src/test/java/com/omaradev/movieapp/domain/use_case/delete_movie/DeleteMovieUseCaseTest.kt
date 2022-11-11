package com.omaradev.movieapp.domain.use_case.delete_movie

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.omaradev.movieapp.data.repository.FakeRepository
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.use_case.get_movie_local.GetLocalMoviesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DeleteMovieUseCaseTest {
    private lateinit var fakeRepository: FakeRepository
    private val moviesToInsert = mutableListOf<Movie>()
    private lateinit var deleteMovieUseCase: DeleteMovieUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        deleteMovieUseCase =DeleteMovieUseCase(fakeRepository)
        moviesToInsert.add(Movie(1, "", "Movie1", "Action", "1990", "1"))
        runBlocking { moviesToInsert.forEach { fakeRepository.insertMovie(it) } }
    }

    @Test
    fun `delete Movie, return True`() = runBlocking {
        deleteMovieUseCase.invoke("1").test {
            Truth.assertThat(awaitItem().data).isEqualTo("Success")
            cancelAndConsumeRemainingEvents()
        }
    }

}