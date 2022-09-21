package com.omaradev.movieapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.omaradev.movieapp.presentation.favorite.FavoriteScreen
import com.omaradev.movieapp.presentation.home.HomeScreen
import com.omaradev.movieapp.presentation.profile.ProfileScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("favorite") {
            FavoriteScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
    }
}