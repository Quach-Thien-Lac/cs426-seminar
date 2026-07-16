package com.example.sanguosuoclient.ui.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanguosuoclient.data.model.SignUpRequest
import com.example.sanguosuoclient.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface SignUpUiState {
    data object Idle : SignUpUiState
    data object Loading : SignUpUiState
    data class Success(val message: String) : SignUpUiState
    data class Error(val message: String) : SignUpUiState
}

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val username = MutableStateFlow("")
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun onUsernameChanged(value: String) {
        username.value = value
    }

    fun onEmailChanged(value: String) {
        email.value = value
    }

    fun onPasswordChanged(value: String) {
        password.value = value
    }

    fun onConfirmPasswordChanged(value: String) {
        confirmPassword.value = value
    }

    fun signUp() {
        // TODO: add form validation (empty fields, email format, password match)
        if (password.value != confirmPassword.value) {
            _uiState.value = SignUpUiState.Error("Passwords do not match")
            return
        }

        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading

            val request = SignUpRequest(
                username = username.value,
                email = email.value,
                password = password.value
            )

            val result = authRepository.signUp(request)

            result.fold(
                onSuccess = {
                    // TODO: navigate to sign in or auto-login
                    _uiState.value = SignUpUiState.Success("Account created! Please sign in.")
                },
                onFailure = { error ->
                    _uiState.value = SignUpUiState.Error(
                        error.message ?: "Sign up failed"
                    )
                }
            )
        }
    }

    fun clearError() {
        _uiState.value = SignUpUiState.Idle
    }
}
