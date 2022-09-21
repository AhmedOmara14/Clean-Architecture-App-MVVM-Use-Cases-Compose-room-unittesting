package com.omaradev.movieapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.omaradev.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.omaradev.movieapp.R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        items = listOf(
                            BottomNavigationItem(
                                stringResource(id = R.string.home),
                                "home",
                                R.drawable.ic_home
                            ),
                            BottomNavigationItem(
                                stringResource(id = R.string.favorite),
                                "favorite",
                                R.drawable.ic_favorite
                            ),
                            BottomNavigationItem(
                                stringResource(id = R.string.profile),
                                "profile",
                                R.drawable.ic_person
                            )
                        ),
                        onItemClick = { navController.navigate(route = it.route) },
                        modifier = Modifier
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White,
                                    Color.White,
                                    colorResource(id = R.color.back_main)
                                ), startY = 100f
                            )
                        )
                ) {
                    Navigation(navHostController = navController)
                }
            }

        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    navController: NavController,
    modifier: Modifier,
    onItemClick: (BottomNavigationItem) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        colorResource(id = R.color.white),
        elevation = 5.dp
    ) {
        items.forEach {
            var selected =
                it.route == navController.currentBackStackEntryAsState().value?.destination?.route

            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(it) },
                selectedContentColor = colorResource(id = R.color.purple_200),
                unselectedContentColor = Color.Black,
                icon = {
                    Column {
                        Icon(painterResource(id = it.icon), contentDescription = it.name)
                        Text(
                            text = it.name,
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.font_bold)),
                            style = TextStyle(fontSize = 10.sp)
                        )
                    }
                }
            )
        }
    }
}
