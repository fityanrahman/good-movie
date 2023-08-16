package id.frame.goodmovies.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.frame.goodmovies.common.Resource
import id.frame.goodmovies.domain.repository.NetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> get() = _state

    init {
        savedStateHandle.get<String>("movie_id")?.let {
            getMovieById(it.toInt())
        }
    }

    private fun getMovieById(id: Int) {
        viewModelScope.launch {
            networkRepository.getMovieById(id = id)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = MovieDetailState(movie = result.data)
                        }

                        is Resource.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(error = result.message ?: "Error!")
                        }
                    }
                }
        }
    }
}
