package com.example.sanguosuoclient.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sanguosuoclient.SanguosuoApplication
import com.example.sanguosuoclient.data.model.UserInfo
import com.example.sanguosuoclient.data.repository.UserRepository
import com.example.sanguosuoclient.data.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProfileUiState {
    data object Idle : ProfileUiState()
    data object Loading : ProfileUiState()
    data class Success(val user: UserInfo) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Idle)
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState

    private val _loggedOut = MutableStateFlow(false)
    val loggedOut: StateFlow<Boolean> = _loggedOut

    fun fetchProfile() {
        val session = sessionManager.session.value
        if (session == null) {
            _profileUiState.value = ProfileUiState.Error("Not signed in")
            return
        }

        viewModelScope.launch {
            _profileUiState.value = ProfileUiState.Loading
            val result = userRepository.fetchUserById(
                token = sessionManager.getToken() ?: "",
                userId = session.userId
            )
            _profileUiState.value = result.fold(
                onSuccess = { ProfileUiState.Success(it) },
                onFailure = { ProfileUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.logout()
            _loggedOut.value = true
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SanguosuoApplication)
                val userRepository = application.container.userRepository
                val sessionManager = application.container.sessionManager
                ProfileViewModel(userRepository = userRepository, sessionManager = sessionManager)
            }
        }
    }
}