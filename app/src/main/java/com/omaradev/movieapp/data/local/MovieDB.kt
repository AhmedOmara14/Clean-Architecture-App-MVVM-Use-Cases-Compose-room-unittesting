package com.omaradev.movieapp.data.local

import androidx.room.*
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.model.all_movies.TypeConverterMovie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterMovie::class)
abstract class MovieDB : RoomDatabase() {
    abstract val movieDao: MovieDao
    companion object {
        const val DATABASE_NAME = "Movie_db"
    }
}