package com.example.sanguosuoclient.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sanguosuoclient.ui.components.SanguosuoButton
import com.example.sanguosuoclient.ui.theme.primaryLight

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
private val CardBorder = Color(0xFFE0C9A6)
private val ErrorRed = Color(0xFFC0392B)
private val BackgroundCream = Color(0xFFFDF6EC)

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundCream)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        when (uiState) {
            is ProfileUiState.Loading, ProfileUiState.Idle -> {
                Box(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = primaryLight)
                }
            }

            is ProfileUiState.Error -> {
                Text(
                    text = uiState.message,
                    color = ErrorRed,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            is ProfileUiState.Success -> {
                ProfileHeader(username = uiState.user.username)
                Spacer(modifier = Modifier.height(24.dp))
                ProfileInfoCard(user = uiState.user)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        SanguosuoButton(
            text = "LOG OUT",
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth().height(52.dp),
        )
    }
}

@Composable
private fun ProfileHeader(username: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .background(Color.White, CircleShape)
                .border(2.dp, primaryLight, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = primaryLight,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = username,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
private fun ProfileInfoCard(user: com.example.sanguosuoclient.data.model.UserInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(16.dp))
            .padding(vertical = 8.dp)
    ) {
        ProfileInfoRow(
            icon = Icons.Default.Person,
            label = "User ID",
            value = user.userId
        )
        ProfileInfoRow(
            icon = Icons.Default.Email,
            label = "Email",
            value = user.email
        )
        ProfileInfoRow(
            icon = Icons.Default.Phone,
            label = "Phone",
            value = user.phone
        )
    }
}

@Composable
private fun ProfileInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = primaryLight,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}