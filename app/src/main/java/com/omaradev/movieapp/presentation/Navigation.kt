package com.omaradev.movieapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.omaradev.movieapp.presentation.favorite.FavoriteScreen
import com.omaradev.movieapp.presentation.home.HomeScreen
import com.omaradev.movieapp.presentation.movie_details.MovieDetailsScreen
import com.omaradev.movieapp.presentation.navigation.Screens
import com.omaradev.movieapp.presentation.profile.ProfileScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(route = Screens.FavoriteScreen.route) {
            FavoriteScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
        composable(
            route = Screens.DetailsScreen.route + "/{imdbID}", arguments = listOf(
                navArgument("imdbID") {
                    defaultValue = ""
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            MovieDetailsScreen(imdbID = it.arguments?.getString("imdbID"))
        }
    }
}