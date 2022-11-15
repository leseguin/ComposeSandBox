@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)

package fr.leane.seguin.composesandbox.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.leane.seguin.data.repository.MovieRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {


    val state = MutableStateFlow(MoviesState.NONE)

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search

    fun updateSearch(newSearch: String) = viewModelScope.launch {
        _search.emit(newSearch)
    }

    @OptIn(FlowPreview::class)
    val movies = search.debounce(1000L).transformLatest { currentSearch ->
        state.emit(MoviesState.LOADING)
        try {
            val movies = if (currentSearch.isNotBlank()) {
                movieRepository.getMovieForSearch(currentSearch)
            } else emptyList()
            emit(movies)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Timber.e(e)
            // TODO Display error && State Error
        } finally {
            state.emit(MoviesState.NONE)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    enum class MoviesState {
        LOADING,
        NONE;

        fun isLoading() = this == LOADING
    }
}