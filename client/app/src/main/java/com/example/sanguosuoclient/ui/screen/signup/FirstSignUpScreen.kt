package com.example.sanguosuoclient.ui.screen.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.components.SanguosuoBackground
import com.example.sanguosuoclient.ui.components.SanguosuoButton
import com.example.sanguosuoclient.ui.components.SanguosuoTextField
import com.example.sanguosuoclient.ui.components.SanguosuoTitle
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme
import com.example.sanguosuoclient.ui.theme.primaryLight

@Composable
fun FirstSignUpScreenRoute(
    onContinue: () -> Unit,
    onBack: () -> Unit,
    viewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.Factory)
) {
    val uiState by viewModel.signUpUiState.collectAsStateWithLifecycle()
    val formState by viewModel.signUpFormState.collectAsStateWithLifecycle()

    FirstSignUpScreen(
        formState = formState,
        uiState = uiState,
        onEmailChanged = viewModel::onEmailChanged,
        onPhoneChanged = viewModel::onPhoneChanged,
        onContinue = onContinue,
        onBack = onBack,
    )
}

@Composable
fun FirstSignUpScreen(
    formState: SignUpFormState,
    uiState: SignUpUiState,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    SanguosuoBackground(
        isWhiteTinted = true,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 5.dp,
                    vertical = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SanguosuoTitle()

            Spacer(modifier = Modifier.height(50.dp))

            if (uiState is SignUpUiState.Error) {
                Text(
                    text = uiState.message,
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            SanguosuoTextField(
                value = formState.email,
                onValueChange = onEmailChanged,
                label = stringResource(R.string.email_label),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                hintText = stringResource(R.string.email_place_holder),
            )

            Spacer(modifier = Modifier.height(20.dp))

            SanguosuoTextField(
                value = formState.phone,
                onValueChange = onPhoneChanged,
                label = stringResource(R.string.phone_label),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                hintText = stringResource((R.string.phone_place_holder))
            )

            Spacer(modifier = Modifier.height(20.dp))

            SanguosuoButton(
                text = stringResource(R.string.continuee),
                onClick = onContinue,
                color = primaryLight
            )
        }
    }
}

@Preview
@Composable
fun FirstSignUpScreenPreview() {
    SanguosuoClientTheme() {
        FirstSignUpScreen(
            formState = SignUpFormState(),
            uiState = SignUpUiState.Idle,
            onEmailChanged = {},
            onPhoneChanged = {},
            onContinue = {},
            onBack = {},
        )
    }
}
