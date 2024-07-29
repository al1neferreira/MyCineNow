package com.devspacecinenow.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devspacecinenow.data.viewModel.MovieListViewModel
import com.devspacecinenow.detail.MovieDetailScreen

@Composable
fun CineNowApp(
    listViewModel: MovieListViewModel

) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movieList") {
        composable(route = "movieList") {
            MovieListScreen(navController, listViewModel)
        }
        composable(route = "movieDetail" + "/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type  = NavType.StringType
            })
        ) { backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getString("itemId"))
            MovieDetailScreen(movieId, navController)
        }
    }
}

