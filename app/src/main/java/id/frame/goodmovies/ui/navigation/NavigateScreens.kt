package id.frame.goodmovies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import id.frame.goodmovies.ui.screen.detail.MovieDetailScreen
import id.frame.goodmovies.ui.screen.genre.GenreMovies
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
        composable(
            Screen.MovieWithGenres.route + "/{genreId}/{genreName}",
            arguments = listOf(
                navArgument("genreId") { type = NavType.IntType },
                navArgument("genreName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val genreId = backStackEntry.arguments?.getInt("genreId")
            val genreName = backStackEntry.arguments?.getString("genreName")
            GenreMovies(
                navController = navController,
                genreId = genreId,
                genreName = genreName
            )
        }
    }

}