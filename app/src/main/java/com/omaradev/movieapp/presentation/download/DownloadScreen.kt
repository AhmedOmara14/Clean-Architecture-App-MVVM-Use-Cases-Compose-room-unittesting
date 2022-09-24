package com.omaradev.movieapp.presentation.download

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.omaradev.movieapp.R
import com.omaradev.movieapp.presentation.navigation.Screens
import com.omaradev.movieapp.presentation.search.component.SearchResult

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DownloadScreen(
    viewModel: DownloadViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val allMoviesState = viewModel.allMoviesState.value
    val context = LocalContext.current
    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.download),
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
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.font_bold)
                    )

                )
            )
            if (allMoviesState.allMovies.isNullOrEmpty()) {
                Text(
                    "Not Founded Movies",
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            Font(R.font.font_bold)
                        )
                    )
                )
            } else {
                allMoviesState.allMovies.let {
                    it.let { it1 ->
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(2),
                            modifier = Modifier.padding(20.dp)
                        ) {
                            itemsIndexed(it1) { index, movie ->
                                SearchResult(
                                    movies = movie, onExecuteMovie = {
                                        navHostController.navigate(
                                            Screens.DetailsScreen.withArgs(
                                                it
                                            )
                                        )
                                    }
                                )
                            }
                        }

                    }
                }
            }
        }
        if (allMoviesState.error.isNotBlank()) {
            Toast.makeText(context, allMoviesState.error, Toast.LENGTH_SHORT).show()
        }
    }
}

