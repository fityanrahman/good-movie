package id.frame.goodmovies.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.frame.goodmovies.data.remote.genre.Genre
import id.frame.goodmovies.domain.repository.NetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {
    private val _stateGenres = mutableStateOf<List<Genre>>(emptyList())
    val stateGenre: State<List<Genre>> get() = _stateGenres

    init {
        getGenres()
    }

    private fun getGenres(){
        viewModelScope.launch {
            _stateGenres.value = networkRepository.getGenres().genres
        }
    }
}