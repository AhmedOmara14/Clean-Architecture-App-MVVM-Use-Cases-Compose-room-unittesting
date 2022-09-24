package com.omaradev.movieapp.presentation.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.omaradev.movieapp.R
import com.omaradev.movieapp.common.Constants
import com.omaradev.movieapp.presentation.navigation.Screens
import com.omaradev.movieapp.presentation.search.component.SearchResult

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val searchMoviesState = viewModel.searchMoviesState.value
    var searchCategory by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val page = viewModel.page.value

    Box() {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.padding(start = 5.dp, end = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "",
                    modifier = Modifier.align(CenterVertically)
                )

                Spacer(modifier = Modifier.width(10.dp))

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = searchCategory,
                    label = { Text(text = "Search") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black
                    ),
                    onValueChange = {
                        searchCategory = it
                    },
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    trailingIcon = {
                        if (searchCategory.isNotEmpty()) {
                            IconButton(onClick = {
                                viewModel.search(searchCategory, Constants.API_TOKEN, page)
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = null
                                )
                            }
                        }

                    }
                )
            }
            if (searchMoviesState.allMoviesResponse == null) {
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
                searchMoviesState.allMoviesResponse.let {
                    it?.let { it1 ->
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(2),
                            modifier = Modifier.padding(20.dp)
                        ) {
                            itemsIndexed(it1) { index, movie ->
                                viewModel.onChangeMoviesListScrollPosition(index)
                                if ((index + 1) >= (page * Constants.PAGE_SIZE) && !searchMoviesState.isLoading) {
                                    viewModel.nextPage(searchCategory)
                                }
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
        if (searchMoviesState.error.isNotBlank()) {
            Toast.makeText(context, searchMoviesState.error, Toast.LENGTH_SHORT).show()
        }
    }
}