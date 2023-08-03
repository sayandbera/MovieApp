package com.sayanx.movieapp.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sayanx.movieapp.screens.DetailsScreen
import com.sayanx.movieapp.screens.HomeScreen

@Composable
fun NavigationSystem() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = EnumScreens.HomeScreen.name) {
        //nav-graph
        composable(route = EnumScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(route = EnumScreens.DetailsScreen.name+"/{movieTitle}",
            arguments = listOf(navArgument("movieTitle") {
                type = NavType.StringType
            })
        ) {
            val title = it.arguments?.getString("movieTitle")
            DetailsScreen(navController, title)
        }

    }
}