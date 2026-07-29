package com.example.sanguosuoclient.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sanguosuoclient.ui.navigation.AppNavHost
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme

@Composable
fun SanguosuoApp(modifier: Modifier = Modifier) {
    SanguosuoClientTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            AppNavHost(navController = navController)
        }
    }
}
