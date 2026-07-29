package com.example.sanguosuoclient.ui.screen.welcome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface WelcomeUiState {
    data object Idle : WelcomeUiState
}

class WelcomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<WelcomeUiState>(WelcomeUiState.Idle)
    val uiState: StateFlow<WelcomeUiState> = _uiState.asStateFlow()

    // TODO: add guest mode navigation logic if needed
}
