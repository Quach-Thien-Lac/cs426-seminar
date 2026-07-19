package com.example.sanguosuoclient.ui.screen.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.components.SanguosuoBackground
import com.example.sanguosuoclient.ui.components.SanguosuoButton
import com.example.sanguosuoclient.ui.components.SanguosuoButtonVariant
import com.example.sanguosuoclient.ui.components.SanguosuoLoadingIndicator
import com.example.sanguosuoclient.ui.components.SanguosuoTextField
import com.example.sanguosuoclient.ui.components.SanguosuoTitle
import com.example.sanguosuoclient.ui.components.SanguosuoTopBar

@Composable
fun SignInScreen(
    onNavigateToSignUp: () -> Unit,
    onBack: () -> Unit,
    viewModel: SignInViewModel = viewModel(factory = SignInViewModel.Factory)
) {
    val uiState by viewModel.signInUiState.collectAsState()
    val username = viewModel.signInFormState.collectAsState().value.username
    val password = viewModel.signInFormState.collectAsState().value.password

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is SignInUiState.Success -> {
                snackbarHostState.showSnackbar(state.message)
                // TODO: navigate to main app
            }
            is SignInUiState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                viewModel.clearError()
            }
            else -> {}
        }
    }

    SanguosuoBackground (isWhiteTinted = true){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 5.dp,
                    vertical = 20.dp
                )
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SanguosuoTitle()

            Spacer(modifier = Modifier.height(64.dp))

            SanguosuoTextField(
                value = username,
                onValueChange = viewModel::onUsernameChanged,
                label = "Username",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                hintText = stringResource(R.string.email_place_holder)
            )

            Spacer(modifier = Modifier.height(24.dp))

            SanguosuoTextField(
                value = password,
                onValueChange = viewModel::onPasswordChanged,
                label = "Password",
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { viewModel.signIn() }
                ),
                hintText = stringResource(R.string.password_place_holder)
            )

//            Spacer(modifier = Modifier.height(6.dp))

            TextButton(
                onClick = {/*OnNavigateToForgotPassword*/},
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

//            Spacer(modifier = Modifier.height(6.dp))

            SanguosuoButton(
                text = stringResource(R.string.sign_in),
                onClick = { viewModel.signIn() },
                enabled = username.isNotBlank() && password.isNotBlank()
            )

            Spacer(modifier = Modifier.height(12.dp))

            SanguosuoButton(
                text = stringResource(R.string.no_account_text),
                onClick = onNavigateToSignUp,
                variant = SanguosuoButtonVariant.Outlined
            )
        }
    }
}
