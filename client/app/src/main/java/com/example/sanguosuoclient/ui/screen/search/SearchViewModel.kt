package com.example.sanguosuoclient.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sanguosuoclient.SanguosuoApplication
import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.data.repository.HeroRepository
import com.example.sanguosuoclient.data.session.SessionManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

sealed class SearchUiState {
    data object Idle : SearchUiState()
    data object Loading : SearchUiState()
    data class Success(val heroes: List<Hero>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val heroRepository: HeroRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState

    // Track the latest search job so we can cancel it if a newer one comes in
    private var searchJob: Job? = null

    init {
        // Auto-search with 400ms debounce whenever the query changes
        _query
            .debounce(400L)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .onEach { q -> performSearch(q) }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
        // If user clears the field, go back to Idle immediately
        if (newQuery.isBlank()) {
            _uiState.value = SearchUiState.Idle
        }
    }

    /** Called when user explicitly presses the keyboard Search action — bypasses debounce. */
    fun onSearch() {
        val q = _query.value.trim()
        if (q.isBlank()) return
        performSearch(q)
    }

    private fun performSearch(q: String) {
        val token = sessionManager.getToken()
        if (token == null) {
            _uiState.value = SearchUiState.Error("Not signed in")
            return
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            val result = heroRepository.searchHeroesByName(token, q)
            _uiState.value = result.fold(
                onSuccess = { SearchUiState.Success(it) },
                onFailure = { SearchUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SanguosuoApplication)
                val heroRepository = application.container.heroRepository
                val sessionManager = application.container.sessionManager
                SearchViewModel(heroRepository = heroRepository, sessionManager = sessionManager)
            }
        }
    }
}
