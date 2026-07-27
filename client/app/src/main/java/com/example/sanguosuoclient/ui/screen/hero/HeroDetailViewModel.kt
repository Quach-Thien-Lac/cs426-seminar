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
    private val heroRepository: HeroRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HeroDetailUiState>(HeroDetailUiState.Idle)
    val uiState: StateFlow<HeroDetailUiState> = _uiState

    fun fetchHero(token: String, heroId: String) {
        viewModelScope.launch {
            _uiState.value = HeroDetailUiState.Loading
            val result = heroRepository.getHeroById(token, heroId)
            _uiState.value = result.fold(
                onSuccess = { HeroDetailUiState.Success(it) },
                onFailure = { HeroDetailUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

//    class Factory(private val heroRepository: HeroRepository) : ViewModelProvider.Factory {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return HeroDetailViewModel(heroRepository) as T
//        }
//    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SanguosuoApplication)
                val heroRepository = application.container.heroRepository
                HeroDetailViewModel(heroRepository = heroRepository)
            }
        }
    }
}
