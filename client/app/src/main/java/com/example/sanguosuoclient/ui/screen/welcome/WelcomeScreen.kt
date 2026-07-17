package com.example.sanguosuoclient.ui.screen.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.components.SanguosuoBackground
import com.example.sanguosuoclient.ui.components.SanguosuoButton
import com.example.sanguosuoclient.ui.components.SanguosuoButtonVariant
import com.example.sanguosuoclient.ui.components.SanguosuoTitle
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme
import com.example.sanguosuoclient.ui.theme.onSurfaceDark
import com.example.sanguosuoclient.ui.theme.primaryLight

@Composable
fun WelcomeScreen(
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    SanguosuoBackground {
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

            Spacer(modifier = Modifier.height(275.dp))

            SanguosuoButton(
                textId = R.string.sign_in,
                onClick = onNavigateToSignIn,
                color = onSurfaceDark
            )

            Spacer(modifier = Modifier.height(12.dp))

            SanguosuoButton(
                textId = R.string.sign_up_with_email,
                onClick = onNavigateToSignUp,
                color = primaryLight
            )

            Spacer(modifier = Modifier.height(12.dp))

            SanguosuoButton(
                textId = R.string.continue_as_guest,
                onClick = { /* TODO: navigate to main app or show snackbar */ },
                variant = SanguosuoButtonVariant.Outlined
            )
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    SanguosuoClientTheme() {
        WelcomeScreen(
            onNavigateToSignIn = {},
            onNavigateToSignUp = {}
        )
    }
}
