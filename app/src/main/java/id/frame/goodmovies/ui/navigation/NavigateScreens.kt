package id.frame.goodmovies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.frame.goodmovies.ui.screen.detail.MovieDetailScreen
import id.frame.goodmovies.ui.screen.home.HomeScreen

@Composable
fun NavigateScreens(
    navController: NavHostController,
//    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.MovieDetail.route + "/{movie_id}") {
            MovieDetailScreen(navController = navController)
        }
    }

}