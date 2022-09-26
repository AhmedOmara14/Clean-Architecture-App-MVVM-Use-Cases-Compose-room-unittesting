package com.omaradev.movieapp.domain.model.all_movies

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.function.Predicate

@Entity(tableName = "Download")
data class Movie(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")
    var id: Int = 0,
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)
