package com.omaradev.movieapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.omaradev.movieapp.domain.model.all_movies.Movie

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movie: Movie)

    @Query("delete from Download where imdbID =:imdbID")
    suspend fun deleteMovie(imdbID: String)

    @Query("SELECT * FROM Download")
    suspend fun getMovies(): List<Movie>

}