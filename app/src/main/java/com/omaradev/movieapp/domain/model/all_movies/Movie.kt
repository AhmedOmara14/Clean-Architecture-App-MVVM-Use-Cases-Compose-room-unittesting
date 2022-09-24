package com.omaradev.movieapp.domain.model.all_movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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