package com.omaradev.movieapp.domain.use_case.insert_movie

import android.util.Log
import com.omaradev.movieapp.data.repository.FakeRepository
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.use_case.get_movie_local.GetLocalMoviesUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class InsertMovieUseCaseTest {
    private lateinit var insertMovieUseCase: InsertMovieUseCase
    private lateinit var getLocalMoviesUseCase: GetLocalMoviesUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        insertMovieUseCase = InsertMovieUseCase(fakeRepository)
        getLocalMoviesUseCase = GetLocalMoviesUseCase(fakeRepository)
    }

    @Test
    fun `insert Movie, return True`() = runBlocking {
        val moviesToInsert = mutableListOf<Movie>()
        moviesToInsert.add(Movie(1, "", "Movie1", "Action", "1990", "1"))
        moviesToInsert.add(Movie(2, "", "Movie2", "Action", "1990", "2"))
        moviesToInsert.add(Movie(3, "", "Movie3", "Action", "1990", "3"))

        moviesToInsert.forEach { insertMovieUseCase(it) }
        val movies = getLocalMoviesUseCase.invoke().first()

        Log.d("TAG", "insert Movie, return True: "+movies)
    }
}