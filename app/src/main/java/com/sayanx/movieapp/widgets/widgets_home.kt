package com.sayanx.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sayanx.movieapp.model.Movie
import com.sayanx.movieapp.model.getMovies
import com.sayanx.movieapp.navigations.EnumScreens

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {

    Column(modifier = Modifier.padding(10.dp)) {
        LazyColumn {
            items(items = movieList) {eachMovie ->
                val expanded = remember {
                    mutableStateOf(false)
                }
                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 125.dp)
                    .padding(6.dp)
                    .clickable(enabled = true) {
                        navController.navigate(EnumScreens.DetailsScreen.name + "/${eachMovie.id}")
                    },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(0.5.dp, Color.LightGray),
                    shadowElevation = 4.dp) {

                    Row(verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start) {

                        AsyncImage(modifier = Modifier
                            .size(width = 180.dp, height = 125.dp)
                            .padding(7.dp)
                            .border(
                                border = BorderStroke(1.5.dp, Color.LightGray),
                                shape = RoundedCornerShape(5.dp)
                            )
                            .clip(RoundedCornerShape(5.dp)),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(eachMovie.images[1])
                                .crossfade(true)
                                .crossfade(true)
                                .build(),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Movie Poster"
                        )

                        Column {

                            Text(text = eachMovie.title, fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(7.dp)
                            )

                            Text(text = "• ${eachMovie.genre}", style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 7.dp), fontWeight = FontWeight.SemiBold
                            )

                            Column(modifier = Modifier.padding( start = 7.dp)) {
                                AnimatedVisibility(visible = expanded.value) {
                                    Column {
                                        Text(buildAnnotatedString {
                                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 12.sp)) {
                                                append("• Director: ")
                                            }
                                            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 12.sp)) {
                                                append(eachMovie.director)
                                            }
                                        })

                                        Text(buildAnnotatedString {
                                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 12.sp)) {
                                                append("• Released: ")
                                            }
                                            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 12.sp)) {
                                                append(eachMovie.year)
                                            }
                                        })
                                    }
                                }

                                Icon(imageVector = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "See More Details",
                                    Modifier.clickable { expanded.value = !expanded.value })
                            }
                        }
                    }
                }
            }
        }
    }
}