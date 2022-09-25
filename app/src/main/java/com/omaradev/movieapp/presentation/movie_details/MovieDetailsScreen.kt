package com.omaradev.movieapp.presentation.movie_details

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.omaradev.movieapp.R
import com.omaradev.movieapp.domain.model.all_movies.Movie
import com.omaradev.movieapp.domain.model.movie_details.MovieDetails
import com.omaradev.movieapp.presentation.main.MainActivity
import com.omaradev.movieapp.presentation.main.MainViewModel
import com.omaradev.movieapp.presentation.movie_details.component.CastMovieItem

@Composable
fun MovieDetailsScreen(
    navHostController: NavHostController,
    imdbID: String?,
    viewModel: DetailsMovieViewModel = hiltViewModel(),
    viewModel_: MainViewModel = hiltViewModel()
) {
    val detailsMovieState = viewModel.detailsMovieState.value
    val movieDetails: MovieDetails? = detailsMovieState.movieDetails
    val context = LocalContext.current

    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
        {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(detailsMovieState.movieDetails?.Poster),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(300.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            ) {
                movieDetails?.Title?.let {
                    Text(
                        it,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(
                                top = 5.dp,
                                bottom = 5.dp,
                                end = 12.dp
                            )
                            .width(150.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontFamily = FontFamily(
                                Font(R.font.font_bold)
                            )

                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_add),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        val movie = Movie(
                            Poster = movieDetails?.Poster + "",
                            Title = movieDetails?.Title + "",
                            Type = movieDetails?.Type + "",
                            Year = movieDetails?.Year + "",
                            imdbID = movieDetails?.imdbID + ""
                        )
                        viewModel.insertMovie(movie)
                        Toast.makeText(context,"Movies is Added to Download",Toast.LENGTH_SHORT).show()

                    }
                )
            }

            RatingBar(
                value = 2.5f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp),
                config = RatingBarConfig()
                    .activeColor(colorResource(id = R.color.active_rate))
                    .inactiveColor(Color.Gray)
                    .stepSize(StepSize.HALF)
                    .numStars(5)
                    .size(15.dp)
                    .padding(2.dp)
                    .style(RatingBarStyle.Normal),
                onValueChange = {
                },
                onRatingChanged = {
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            ) {
                movieDetails?.Runtime?.let {
                    Text(
                        it,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontFamily = FontFamily(
                                Font(R.font.font_bold)
                            )

                        )
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Card(
                    modifier = Modifier.size(5.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "", modifier = Modifier.background(Color.Gray))
                }
                Spacer(modifier = Modifier.width(5.dp))

                movieDetails?.Genre?.let {
                    Text(
                        it,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(
                                top = 5.dp,
                                bottom = 5.dp,
                                end = 12.dp
                            )
                            .width(150.dp),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontFamily = FontFamily(
                                Font(R.font.font_bold)
                            )

                        )
                    )
                }


            }
            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "Plot",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(
                        top = 5.dp,
                        start = 15.dp,
                        bottom = 5.dp,
                        end = 15.dp
                    )
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.font_bold)
                    )

                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            movieDetails?.Plot?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(
                            top = 5.dp,
                            start = 15.dp,
                            bottom = 5.dp,
                            end = 15.dp
                        )
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            Font(R.font.font_regular)
                        )

                    )
                )
            }


            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "Cast",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(
                        top = 5.dp,
                        start = 15.dp,
                        bottom = 5.dp,
                        end = 15.dp
                    )
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.font_bold)
                    )

                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            CastMovieItem(moviesList = getAllCast())
        }

        if (detailsMovieState.error.isNotBlank()) {
            Toast.makeText(context, detailsMovieState.error, Toast.LENGTH_SHORT).show()
        }
        if (detailsMovieState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (detailsMovieState.movieDetails?.Response == "False") {
            Toast.makeText(context, detailsMovieState.movieDetails.Error, Toast.LENGTH_SHORT).show()
        }
    }
}