package com.example.sanguosuoclient.ui.screen.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.components.SanguosuoBackground
import com.example.sanguosuoclient.ui.components.SanguosuoButton
import com.example.sanguosuoclient.ui.components.SanguosuoButtonVariant
import com.example.sanguosuoclient.ui.components.SanguosuoTextField
import com.example.sanguosuoclient.ui.components.SanguosuoTitle
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme
import com.example.sanguosuoclient.ui.theme.primaryLight

@Composable
fun SignUpScreenRoute(
    onNavigateToSignIn: () -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    viewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.Factory)
) {
    val uiState by viewModel.signUpUiState.collectAsStateWithLifecycle()
    val formState by viewModel.signUpFormState.collectAsStateWithLifecycle()

    SignUpScreen(
        formState = formState,
        uiState = uiState,
        onEmailChanged = viewModel::onEmailChanged,
        onPhoneChanged = viewModel::onPhoneChanged,
        onUsernameChanged = viewModel::onUsernameChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,
        onNextPage = viewModel::onNextPage,
        onPreviousPage = viewModel::onPreviousPage,
        onNavigateToSignIn = onNavigateToSignIn,
        onSubmit = viewModel::signUp,
        onBack = onBack,
        modifier = Modifier.fillMaxSize()
    )
    LaunchedEffect(uiState) {
        if (uiState is SignUpUiState.Success) {
            onSubmit()
            viewModel.clearError()
        }
    }
}

@Composable
fun SignUpScreen(
    formState: SignUpFormState,
    uiState: SignUpUiState,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showPassword by rememberSaveable { mutableStateOf(false) }
    var showConfirmPassword by rememberSaveable { mutableStateOf(false) }

    SanguosuoBackground(
        isWhiteTinted = true,
        modifier = Modifier.fillMaxSize()
    ) {
        Box (
            modifier = modifier
        ) {
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
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                SanguosuoTitle()

                Spacer(modifier = Modifier.height(37.dp))

                if (uiState is SignUpUiState.Error) {
                    Text(
                        text = uiState.message,
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (formState.isFirstPage) {
                    SanguosuoTextField(
                        value = formState.email,
                        onValueChange = onEmailChanged,
                        label = stringResource(R.string.email_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        hintText = stringResource(R.string.email_place_holder),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SanguosuoTextField(
                        value = formState.phone,
                        onValueChange = onPhoneChanged,
                        label = stringResource(R.string.phone_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        hintText = stringResource((R.string.phone_place_holder)),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SanguosuoButton(
                        text = stringResource(R.string.next),
                        onClick = onNextPage,
                        color = primaryLight
                    )
                } else {
                    SanguosuoTextField(
                        value = formState.username,
                        onValueChange = onUsernameChanged,
                        label = stringResource(R.string.username_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        hintText = stringResource((R.string.username_place_holder)),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SanguosuoTextField(
                        value = formState.password,
                        onValueChange = onPasswordChanged,
                        label = stringResource(R.string.password_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        hintText = stringResource((R.string.password_place_holder)),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    showPassword = !showPassword
                                }
                            ) {
                                Icon(
                                    painter = if (showPassword) painterResource(R.drawable.visibility_off) else painterResource(R.drawable.visibility),
                                    contentDescription = "",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SanguosuoTextField(
                        value = formState.confirmPassword,
                        onValueChange = onConfirmPasswordChanged,
                        label = stringResource(R.string.confirm_password_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        hintText = stringResource((R.string.password_place_holder)),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    showConfirmPassword = !showConfirmPassword
                                }
                            ) {
                                Icon(
                                    painter = if (showConfirmPassword) painterResource(R.drawable.visibility_off) else painterResource(R.drawable.visibility),
                                    contentDescription = "",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SanguosuoButton(
                        text = stringResource(R.string.back),
                        onClick = onPreviousPage,
                        variant = SanguosuoButtonVariant.Outlined
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SanguosuoButton(
                        text = stringResource(R.string.sign_up),
                        onClick = onSubmit,
                        color = primaryLight
                    )
                }
            }

            if (uiState is SignUpUiState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = primaryLight)
                }
            }
            else if (uiState is SignUpUiState.Success) {
                AlertDialog(
                    properties = DialogProperties(
                        dismissOnBackPress = false,
                        dismissOnClickOutside = false
                    ),
                    onDismissRequest = {},
                    title = {
                        Text(text = stringResource(R.string.registration_successful))
                    },
                    text = {
                        Text(text = uiState.message)
                    },
                    confirmButton = {
                        Button(onClick = onNavigateToSignIn) {
                            Text(stringResource(R.string.sign_in_title))
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SanguosuoClientTheme {
        SignUpScreen(
            formState = SignUpFormState(),
            uiState = SignUpUiState.Success("cc"),
            onEmailChanged = {},
            onPhoneChanged = {},
            onUsernameChanged = {},
            onPasswordChanged = {},
            onConfirmPasswordChanged = {},
            onNextPage = {},
            onPreviousPage = {},
            onNavigateToSignIn = {},
            onSubmit = {},
            onBack = {}
        )
    }
}
