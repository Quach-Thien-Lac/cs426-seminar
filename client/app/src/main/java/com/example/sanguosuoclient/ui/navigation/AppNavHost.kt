package com.example.sanguosuoclient.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sanguosuoclient.SanguosuoApplication
import com.example.sanguosuoclient.ui.MainScaffold
import com.example.sanguosuoclient.ui.components.SanguosuoBottomBar
import com.example.sanguosuoclient.ui.components.SanguosuoTopBar
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailScreen
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailScreenRoute
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailUiState
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailViewModel
import com.example.sanguosuoclient.ui.screen.welcome.WelcomeScreen
import com.example.sanguosuoclient.ui.screen.signin.SignInScreenRoute
import com.example.sanguosuoclient.ui.screen.signup.SignUpScreenRoute
import com.example.sanguosuoclient.ui.screen.search.SearchScreen
import com.example.sanguosuoclient.ui.screen.search.SearchScreenRoute
import com.example.sanguosuoclient.ui.screen.search.SearchViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) NavRoute.Main.route else NavRoute.Welcome.route
    ) {
        composable(NavRoute.Welcome.route) {
            WelcomeScreen(
                onNavigateToSignIn = {
                    navController.navigate(NavRoute.SignIn.route)
                },
                onNavigateToSignUp = {
                    navController.navigate(NavRoute.SignUp.route)
                }
            )
        }

        composable(NavRoute.SignIn.route) {
            SignInScreenRoute(
                onNavigateToSignUp = {
                    navController.navigate(NavRoute.SignUp.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                onSubmit = {
                    navController.navigate(NavRoute.Main.route) {
                        popUpTo(NavRoute.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoute.SignUp.route) {
            SignUpScreenRoute(
                onNavigateToSignIn = {
                    navController.navigate(NavRoute.SignIn.route)
                },
                onSubmit = {},
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoute.Main.route) {
            MainScaffold(
                onLoggedOut = {
                    navController.navigate(NavRoute.Welcome.route) {
                        popUpTo(NavRoute.Main.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
