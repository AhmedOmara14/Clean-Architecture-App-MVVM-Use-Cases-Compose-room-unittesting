package com.omaradev.movieapp.presentation.search.component

import android.app.appsearch.SearchResult
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.omaradev.movieapp.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.omaradev.movieapp.domain.model.all_movies.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResult(
    movies: Movie, onExecuteMovie: (String) -> Unit
) {

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
                    .clickable { onExecuteMovie(movies.imdbID) }

            ) {
                Image(
                    painter = rememberAsyncImagePainter(movies.Poster),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(170.dp)
                        .height(220.dp)
                )

                Card(
                    elevation = 4.dp,
                    shape = CircleShape,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = "Profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                    }
                }


            }

        }
        Text(
            movies.Title,
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


