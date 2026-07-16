package com.example.sanguosuoclient.ui.screen.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.ui.components.SanguosuoBackground
import com.example.sanguosuoclient.ui.components.SanguosuoButton
import com.example.sanguosuoclient.ui.components.SanguosuoButtonVariant

@Composable
fun WelcomeScreen(
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    SanguosuoBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sanguosuo",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Tam Quốc Sách",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(48.dp))

            SanguosuoButton(
                text = "Sign In",
                onClick = onNavigateToSignIn
            )

            Spacer(modifier = Modifier.height(12.dp))

            SanguosuoButton(
                text = "Sign Up",
                onClick = onNavigateToSignUp,
                variant = SanguosuoButtonVariant.Secondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            SanguosuoButton(
                text = "Continue as Guest",
                onClick = { /* TODO: navigate to main app or show snackbar */ },
                variant = SanguosuoButtonVariant.Text
            )
        }
    }
}
