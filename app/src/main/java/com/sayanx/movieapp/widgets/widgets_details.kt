package com.sayanx.movieapp.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sayanx.movieapp.model.Movie

@Composable
fun WidgetsDetails(matchedMovie: Movie) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)) {

        Text(textAlign = TextAlign.Center,text = matchedMovie.title, fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium, modifier = Modifier
                .padding(7.dp)
                .fillMaxWidth()
        )

        ImageScroll(matchedMovie) // calling the function from bellow

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 15.sp)) {
                append("• Category: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 15.sp)) {
                append(matchedMovie.genre)
            }
        }, modifier = Modifier.padding(top = 10.dp))

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 15.sp)) {
                append("• Released On: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 15.sp)) {
                append(matchedMovie.year)
            }
        })

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 15.sp)) {
                append("• Directors: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 15.sp)) {
                append(matchedMovie.director)
            }
        })

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 15.sp)) {
                append("• Rating: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 15.sp)) {
                append(matchedMovie.rating)
            }
        })

        Divider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 15.sp)) {
                append("• Plot: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 15.sp)) {
                append(matchedMovie.plot)
            }
        })

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 15.sp)) {
                append("• Actors: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = 15.sp)) {
                append(matchedMovie.actors)
            }
        }, modifier = Modifier.padding(top = 7.dp))
    }
}

@Composable
private fun ImageScroll(matchedMovie: Movie) {
    LazyRow {
        items(matchedMovie.images) { imageUrl ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(7.dp)
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            border = BorderStroke(4.dp, Color.DarkGray),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .clip(RoundedCornerShape(5.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Movie Poster"
                )
            }
        }
    }
}