package com.example.sanguosuoclient.ui.screen.hero

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

sealed class HeroDetailUiState {
    data object Idle : HeroDetailUiState()
    data object Loading : HeroDetailUiState()
    data class Success(val hero: Hero) : HeroDetailUiState()
    data class Error(val message: String) : HeroDetailUiState()
}

class HeroDetailViewModel(
    private val heroRepository: HeroRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _heroDetailUiState = MutableStateFlow<HeroDetailUiState>(HeroDetailUiState.Idle)
    val heroDetailUiState: StateFlow<HeroDetailUiState> = _heroDetailUiState

    fun fetchHero(heroId: String) {
        val token = sessionManager.getToken()
        if (token == null) {
            _heroDetailUiState.value = HeroDetailUiState.Error("Not signed in")
            return
        }

        viewModelScope.launch {
            _heroDetailUiState.value = HeroDetailUiState.Loading
            val result = heroRepository.getHeroById(token, heroId)
            _heroDetailUiState.value = result.fold(
                onSuccess = { HeroDetailUiState.Success(it) },
                onFailure = { HeroDetailUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SanguosuoApplication)
                val heroRepository = application.container.heroRepository
                val sessionManager = application.container.sessionManager
                HeroDetailViewModel(heroRepository = heroRepository, sessionManager = sessionManager)
            }
        }
    }
}
