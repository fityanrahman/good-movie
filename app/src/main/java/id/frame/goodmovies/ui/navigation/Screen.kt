package id.frame.goodmovies.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object MovieDetail: Screen(route = "movie_detail")
    object MovieWithGenres: Screen(route = "movie_with_genres")

}