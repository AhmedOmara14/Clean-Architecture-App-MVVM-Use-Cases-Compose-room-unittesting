package com.omaradev.movieapp.presentation.movie_details.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.omaradev.movieapp.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.omaradev.movieapp.presentation.movie_details.MovieCast

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CastMovieItem(
    moviesList: List<MovieCast>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, bottom = 90.dp)
    ) {
        LazyRow(
        ) {
            items(moviesList){movies->
                Column(modifier = Modifier.padding(end = 5.dp, bottom = 10.dp)) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(Color.LightGray)
                                .wrapContentHeight()
                                .wrapContentWidth()

                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(movies.img),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(90.dp)
                                    .height(90.dp)
                            )



                        }

                    }
                    Text(
                        movies.name,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(
                                top = 5.dp,
                                bottom = 5.dp,
                                end = 12.dp
                            )
                            .width(90.dp),
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

        }
    }


}