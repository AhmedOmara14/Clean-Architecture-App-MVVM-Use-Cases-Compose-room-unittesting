package com.omaradev.movieapp.presentation.home.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.omaradev.movieapp.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.movieapp.presentation.home.MovieCategory

@Composable
fun CategoryItem(
    categoryList: List<MovieCategory>,
    onExecuteSearch: (String) -> Unit,
    Selected: String?
) {
    LazyRow(modifier = Modifier.padding(start = 15.dp)) {
        items(categoryList) {
            Card(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { onExecuteSearch(it.value) }
            ) {
                Log.d("TAG", "CategoryItem: "+Selected)
                Box(
                    modifier = Modifier.background(
                        color =
                        if (it.value.contains(Selected.toString()))
                            colorResource(id = R.color.purple_200)
                        else Color.Gray
                    )
                ) {
                    Text(
                        it.value,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 5.dp,
                            bottom = 5.dp,
                            end = 12.dp
                        ),
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.White,
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