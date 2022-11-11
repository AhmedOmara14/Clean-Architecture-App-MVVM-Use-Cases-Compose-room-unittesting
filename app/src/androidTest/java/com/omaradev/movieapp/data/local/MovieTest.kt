package com.omaradev.movieapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.omaradev.movieapp.domain.model.all_movies.Movie
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class MovieTest {
    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)
    private lateinit var dao: MovieDao
    @Inject
    @Named("test_db")
    lateinit var db: MovieDB
    @Before
    fun setUp() {
        /**
         * Before Hilt
         * val context = ApplicationProvider.getApplicationContext<Context>()
         * db = Room.inMemoryDatabaseBuilder(context, MovieDB::class.java).build()
         */
        hiltAndroidRule.inject()
        dao = db.movieDao
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertMovieToDataBase() = runBlocking {
        val movie = Movie(1, "", "Title", "Action", "1990", "1")
        dao.insert(movie)
        val allMovies = dao.getMovies()
        assertThat(allMovies).contains(movie)
    }

    @Test
    fun deleteMovieFromDataBase() = runBlocking {
        val movie = Movie(1, "", "Title", "Action", "1990", "1")
        dao.insert(movie)
        dao.deleteMovie(movie.imdbID)
        val allMovies = dao.getMovies()
        assertThat(allMovies).doesNotContain(movie)
    }
}