package com.example.sanguosuoclient.ui.screen.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sanguosuoclient.di.LocalAppContainer
import com.example.sanguosuoclient.ui.components.SanguosuoBackground
import com.example.sanguosuoclient.ui.components.SanguosuoButton
import com.example.sanguosuoclient.ui.components.SanguosuoButtonVariant
import com.example.sanguosuoclient.ui.components.SanguosuoLoadingIndicator
import com.example.sanguosuoclient.ui.components.SanguosuoTextField
import com.example.sanguosuoclient.ui.components.SanguosuoTopBar

@Composable
fun SignUpScreen(
    onBack: () -> Unit
) {
    val appContainer = LocalAppContainer.current
    val viewModel: SignUpViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SignUpViewModel(appContainer.authRepository) as T
            }
        }
    )

    val uiState by viewModel.uiState.collectAsState()
    val username by viewModel.username.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is SignUpUiState.Success -> {
                snackbarHostState.showSnackbar(state.message)
                // TODO: navigate to sign in screen
            }
            is SignUpUiState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                viewModel.clearError()
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            SanguosuoTopBar(
                title = "Sign Up",
                showBack = true,
                onBack = onBack
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (uiState is SignUpUiState.Loading) {
                SanguosuoLoadingIndicator()
            } else {
                SanguosuoBackground {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(32.dp))

//                        SanguosuoTextField(
//                            value = username,
//                            onValueChange = viewModel::onUsernameChanged,
//                            label = "Username",
//                            keyboardOptions = KeyboardOptions(
//                                keyboardType = KeyboardType.Text,
//                                imeAction = ImeAction.Next
//                            ),
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        SanguosuoTextField(
//                            value = email,
//                            onValueChange = viewModel::onEmailChanged,
//                            label = "Email",
//                            keyboardOptions = KeyboardOptions(
//                                keyboardType = KeyboardType.Email,
//                                imeAction = ImeAction.Next
//                            )
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        SanguosuoTextField(
//                            value = password,
//                            onValueChange = viewModel::onPasswordChanged,
//                            label = "Password",
//                            visualTransformation = PasswordVisualTransformation(),
//                            keyboardOptions = KeyboardOptions(
//                                keyboardType = KeyboardType.Password,
//                                imeAction = ImeAction.Next
//                            )
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        SanguosuoTextField(
//                            value = confirmPassword,
//                            onValueChange = viewModel::onConfirmPasswordChanged,
//                            label = "Confirm Password",
//                            visualTransformation = PasswordVisualTransformation(),
//                            keyboardOptions = KeyboardOptions(
//                                keyboardType = KeyboardType.Password,
//                                imeAction = ImeAction.Done
//                            ),
//                            keyboardActions = KeyboardActions(
//                                onDone = { viewModel.signUp() }
//                            )
//                        )

                        Spacer(modifier = Modifier.height(24.dp))

//                        SanguosuoButton(
//                            text = "Sign Up",
//                            onClick = { viewModel.signUp() },
//                            enabled = username.isNotBlank() && email.isNotBlank()
//                                    && password.isNotBlank() && confirmPassword.isNotBlank()
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        SanguosuoButton(
//                            text = "Already have an account? Sign In",
//                            onClick = onBack,
//                            variant = SanguosuoButtonVariant.Text
//                        )
                    }
                }
            }
        }
    }
}
