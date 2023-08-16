package id.frame.goodmovies.ui.screen.home

import id.frame.goodmovies.domain.model.HomeList

data class HomeState (
    val homeList: List<HomeList> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)