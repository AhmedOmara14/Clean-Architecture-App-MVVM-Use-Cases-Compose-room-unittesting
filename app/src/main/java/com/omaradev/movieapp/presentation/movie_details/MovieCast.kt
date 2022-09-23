package com.omaradev.movieapp.presentation.movie_details
import com.omaradev.movieapp.R
enum class MovieCast(val img: Int) {
    MatthewMercer(R.drawable.img1),
    DavidVincent(R.drawable.img2),
    DaisukeOno(R.drawable.img3),
    UnshodShizuka(R.drawable.img4),
    PhillipReich(R.drawable.img5)
}

fun getAllCast(): List<MovieCast> {
    return listOf(
        MovieCast.DaisukeOno,
        MovieCast.DavidVincent,
        MovieCast.MatthewMercer,
        MovieCast.PhillipReich,
        MovieCast.UnshodShizuka
    )
}