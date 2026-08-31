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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SearchUiState {
    data object Idle : SearchUiState()
    data object Loading : SearchUiState()
    data class Success(val heroes: List<Hero>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}

class SearchViewModel(
    private val heroRepository: HeroRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    fun onSearch(token: String) {
        val q = _query.value.trim()

        if (q.isBlank()) return

        viewModelScope.launch {
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
                SearchViewModel(heroRepository = heroRepository)
            }
        }
    }
}
