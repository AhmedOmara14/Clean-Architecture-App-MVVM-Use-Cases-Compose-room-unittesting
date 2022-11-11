package com.omaradev.movieapp.di

import android.content.Context
import androidx.room.Room
import com.omaradev.movieapp.data.local.MovieDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModuleTest {

    @Provides
    @Named("test_db")
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, MovieDB::class.java).build()
}