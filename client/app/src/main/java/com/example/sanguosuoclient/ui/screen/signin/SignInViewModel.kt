package com.example.sanguosuoclient.ui.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sanguosuoclient.SanguosuoApplication
import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface SignInUiState {
    data object Idle : SignInUiState
    data object Loading : SignInUiState
    data class Success(val message: String) : SignInUiState
    data class Error(val message: String) : SignInUiState
}

data class SignInFormState(
    val username: String = "",
    val password: String = ""
)

class SignInViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _signInFormState = MutableStateFlow<SignInFormState>(SignInFormState())
    val signInFormState: StateFlow<SignInFormState> = _signInFormState.asStateFlow()

    private val _signInUIState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val signInUiState: StateFlow<SignInUiState> = _signInUIState.asStateFlow()

    fun onUsernameChanged(value: String) {
        _signInFormState.update {
            currentState ->
            currentState.copy(username = value)
        }
    }

    fun onPasswordChanged(value: String) {
        _signInFormState.update {
            currentState ->
            currentState.copy(password = value)
        }
    }

    fun signIn() {
        val currentUsername = _signInFormState.value.username
        val currentPassword = _signInFormState.value.password

        if (currentUsername.isEmpty() || currentPassword.isEmpty()) {
            _signInUIState.value = SignInUiState.Error("Please enter both username and password!")
            return
        }

        viewModelScope.launch {
            _signInUIState.value = SignInUiState.Loading

            val request = SignInRequest(
                username = _signInFormState.value.username,
                password = _signInFormState.value.password
            )

            val result = authRepository.signIn(request)

            result.fold(
                onSuccess = {
                    // TODO: store token, navigate to main app
                    _signInUIState.value = SignInUiState.Success("Welcome back!")
                },
                onFailure = { error ->
                    _signInUIState.value = SignInUiState.Error(
                        error.message ?: "Sign in failed"
                    )
                }
            )
        }
    }

    fun clearError() {
        _signInUIState.value = SignInUiState.Idle
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SanguosuoApplication)
                val authRepository = application.container.authRepository
                SignInViewModel(authRepository = authRepository)
            }
        }
    }
}
