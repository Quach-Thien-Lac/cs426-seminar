package com.example.sanguosuoclient.ui.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface SignInUiState {
    data object Idle : SignInUiState
    data object Loading : SignInUiState
    data class Success(val message: String) : SignInUiState
    data class Error(val message: String) : SignInUiState
}

class SignInViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val username = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun onUsernameChanged(value: String) {
        username.value = value
    }

    fun onPasswordChanged(value: String) {
        password.value = value
    }

    fun signIn() {
        // TODO: add form validation (empty fields, etc.)
        viewModelScope.launch {
            _uiState.value = SignInUiState.Loading

            val request = SignInRequest(
                username = username.value,
                password = password.value
            )

            val result = authRepository.signIn(request)

            result.fold(
                onSuccess = {
                    // TODO: store token, navigate to main app
                    _uiState.value = SignInUiState.Success("Welcome back!")
                },
                onFailure = { error ->
                    _uiState.value = SignInUiState.Error(
                        error.message ?: "Sign in failed"
                    )
                }
            )
        }
    }

    fun clearError() {
        _uiState.value = SignInUiState.Idle
    }
}
