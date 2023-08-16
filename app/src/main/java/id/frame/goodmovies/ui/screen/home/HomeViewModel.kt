package id.frame.goodmovies.ui.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.frame.goodmovies.common.Resource
import id.frame.goodmovies.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private  val networkRepository: NetworkRepository
): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state get() = _state

    init {
        getHomeDatas()
    }

    private fun getHomeDatas() {
        networkRepository.getHomeMovies().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeState(homeList = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeState(error = result.message ?: "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }
}