package com.example.sanguosuoclient.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.sanguosuoclient.SanguosuoApplication
import com.example.sanguosuoclient.ui.navigation.AppNavHost
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme
import androidx.compose.runtime.collectAsState

@Composable
fun SanguosuoApp(modifier: Modifier = Modifier) {
    val application = LocalContext.current.applicationContext as SanguosuoApplication
    val sessionManager = application.container.sessionManager

    var isSessionRestored by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        sessionManager.restoreSession()
        isSessionRestored = true
    }

    SanguosuoClientTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (!isSessionRestored) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                    isLoggedIn = sessionManager.session.collectAsState().value != null
                )
            }
        }
    }
}
