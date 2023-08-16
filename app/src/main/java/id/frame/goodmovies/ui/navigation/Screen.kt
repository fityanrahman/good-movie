package id.frame.goodmovies.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
}