package com.sayanx.movieapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.sayanx.movieapp.model.getMovies
import com.sayanx.movieapp.widgets.WidgetsDetails


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    // storing the movie in the variable that matches the id
    val matchedMovie = getMovies().filter {//extension function
        it.id == movieId
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie Details", color = Color.Black, fontWeight = FontWeight.SemiBold) },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFFFABDD)),
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back",
                        modifier = Modifier.clickable(enabled = true) {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {

            WidgetsDetails(matchedMovie.first())

        }
    }
}