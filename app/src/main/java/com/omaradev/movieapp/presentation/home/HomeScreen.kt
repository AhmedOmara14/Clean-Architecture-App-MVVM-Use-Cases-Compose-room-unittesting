package com.omaradev.movieapp.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.omaradev.movieapp.R
import com.omaradev.movieapp.presentation.home.component.LastReleaseItem


@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Row(modifier = Modifier.padding(start = 20.dp, end =20.dp, top = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) {
                Text(
                    text = "Hello Dev", style = TextStyle(
                        color = Color.Black, fontSize = 18.sp, fontFamily = FontFamily(
                            Font(R.font.font_bold)
                        )
                    )
                )
                Text(
                    text = "Watch your favourite movies \nand Tv Shows here", style = TextStyle(
                        color = Color.Black, fontSize = 12.sp, fontFamily = FontFamily(
                            Font(R.font.font_regular)
                        )
                    )
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, color = colorResource(id = R.color.border))
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.back_search))
                    .padding(all = 10.dp)
                    .background(color = colorResource(id = R.color.back_search)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Search", textAlign = TextAlign.Start, style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.font_bold)
                        )
                    )
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.clickable { })
            }

        }
        Text(
            "Latest Release", textAlign = TextAlign.Start, style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(R.font.font_bold)
                )
            ), modifier = Modifier.padding(start = 20.dp, end =20.dp, top = 10.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        LastReleaseItem("")

    }
}

