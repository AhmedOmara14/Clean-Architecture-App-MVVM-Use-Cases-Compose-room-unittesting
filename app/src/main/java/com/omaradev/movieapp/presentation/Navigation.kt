package com.omaradev.movieapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.omaradev.movieapp.presentation.download.DownloadScreen
import com.omaradev.movieapp.presentation.home.HomeScreen
import com.omaradev.movieapp.presentation.movie_details.MovieDetailsScreen
import com.omaradev.movieapp.presentation.navigation.Screens
import com.omaradev.movieapp.presentation.search.SearchScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(route = Screens.DownloadScreen.route) {
            DownloadScreen(navHostController = navHostController)
        }
        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navHostController = navHostController)
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
            MovieDetailsScreen(
                imdbID = it.arguments?.getString("imdbID"),
                navHostController = navHostController
            )
        }
    }
}