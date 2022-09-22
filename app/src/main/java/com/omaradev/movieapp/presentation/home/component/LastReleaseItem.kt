package com.omaradev.movieapp.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.util.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.*
import com.omaradev.movieapp.R
import com.omaradev.movieapp.domain.model.all_movies.Movie
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LastReleaseItem(items: List<Movie>) {
    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialPage = items.size / 2
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(),

            ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .padding(10.dp, 0.dp, 10.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {

                val movies = items[page]
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .align(Alignment.Center)
                        .width(160.dp)
                        .height(220.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(movies.Poster),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(160.dp)
                            .height(220.dp)

                    )

                    Card(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(Color.White)
                    ) {

                    }
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

        }

        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
                .background(Color.White), indicatorWidth = 0.dp
        )

    }

}

