package com.example.sanguosuoclient.ui.screen.saved

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SavedHeroesUiState {
    data object Idle : SavedHeroesUiState()
    data object Loading : SavedHeroesUiState()
    data class Success(val heroes: List<Hero>) : SavedHeroesUiState()
    data class Error(val message: String) : SavedHeroesUiState()
}

class SavedHeroesViewModel(
    private val heroRepository: HeroRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _savedHeroesUiState = MutableStateFlow<SavedHeroesUiState>(SavedHeroesUiState.Idle)
    val savedHeroesUiState: StateFlow<SavedHeroesUiState> = _savedHeroesUiState

    fun fetchSavedHeroes() {
        val session = sessionManager.session.value
        if (session == null) {
            _savedHeroesUiState.value = SavedHeroesUiState.Error("Not signed in")
            return
        }

        viewModelScope.launch {
            _savedHeroesUiState.value = SavedHeroesUiState.Loading
            val result = heroRepository.getSavedHeroes(
                token = sessionManager.getToken() ?: "",
                userId = session.userId
            )
            _savedHeroesUiState.value = result.fold(
                onSuccess = { SavedHeroesUiState.Success(it) },
                onFailure = { SavedHeroesUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    fun unsaveHero(heroId: String) {
        println("Unsave initiated for hero: $heroId")

        val session = sessionManager.session.value
        if (session == null) {
            println("Unsave aborted: Session is null")
            return
        }

        val token = sessionManager.getToken()
        if (token == null) {
            println("Unsave aborted: Token is null")
            return
        }

        println("Validation passed. Launching repository call...")
        viewModelScope.launch {
            val result = heroRepository.unsaveHero(token, session.userId, heroId)

            result.onFailure { error ->
                println("Unsave failed client-side: ${error.message}")
                error.printStackTrace()
            }

            _savedHeroesUiState.value = SavedHeroesUiState.Success(heroRepository.savedHeroes.value)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SanguosuoApplication)
                val heroRepository = application.container.heroRepository
                val sessionManager = application.container.sessionManager
                SavedHeroesViewModel(heroRepository = heroRepository, sessionManager = sessionManager)
            }
        }
    }
}