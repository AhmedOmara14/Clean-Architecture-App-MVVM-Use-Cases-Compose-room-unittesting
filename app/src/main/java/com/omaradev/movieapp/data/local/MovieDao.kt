package com.omaradev.movieapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.omaradev.movieapp.domain.model.all_movies.Movie

@Dao
interface MovieDao {
    @Insert
    fun insert(movie: Movie)

    @Query("delete from Download where imdbID =:imdbID")
    fun deleteMovie(imdbID: String)

    @Query("SELECT * FROM Download")
    fun getMovies(): List<Movie>

}