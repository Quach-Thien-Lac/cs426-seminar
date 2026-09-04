package com.example.sanguosuoclient.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreenRoute(
    onLoggedOut: () -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)
) {
    val profileUiState by viewModel.profileUiState.collectAsStateWithLifecycle()
    val loggedOut by viewModel.loggedOut.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchProfile()
    }

    LaunchedEffect(loggedOut) {
        if (loggedOut) {
            onLoggedOut()
        }
    }

    ProfileScreen(
        uiState = profileUiState,
        onLogoutClick = viewModel::logout,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        when (uiState) {
            is ProfileUiState.Success -> {
                Column() {
                    Text("Logged in as ${uiState.user.username}")
                    Text(uiState.user.email)
                    Text(uiState.user.phone)
                }
            }
            is ProfileUiState.Loading -> Text("Loading...")
            is ProfileUiState.Error -> Text("Error: ${uiState.message}")
            is ProfileUiState.Idle -> {}
        }
        Button(onClick = onLogoutClick) {
            Text("Log Out")
        }
    }
}