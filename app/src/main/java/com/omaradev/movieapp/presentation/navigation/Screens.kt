package com.omaradev.movieapp.presentation.navigation

import android.util.Log

sealed class Screens(val route:String){
    object HomeScreen :Screens("home_screen")
    object DetailsScreen :Screens("details_screen")
    object FavoriteScreen :Screens("favorite_screen")

    fun withArgs(vararg args:String):String{
       return buildString {
           append(route)
           Log.d("TAG", "withArgs: "+ args[0])
           args.forEach { _ ->
               append("/${args[0]}")
           }
       }
    }
}
