package com.example.sanguosuoclient.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sanguosuoclient.SanguosuoApplication
import com.example.sanguosuoclient.ui.MainScaffold
import com.example.sanguosuoclient.ui.screen.welcome.WelcomeScreen
import com.example.sanguosuoclient.ui.screen.signin.SignInScreenRoute
import com.example.sanguosuoclient.ui.screen.signup.SignUpScreenRoute
import com.example.sanguosuoclient.ui.screen.search.SearchScreen
import com.example.sanguosuoclient.ui.screen.search.SearchViewModel

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    val context = LocalContext.current
    val app = context.applicationContext as SanguosuoApplication
    val searchViewModel: SearchViewModel = viewModel(
        factory = SearchViewModel.Factory(app.container.heroRepository)
    )

    // Use the hardcoded bypass token since the server expects JWTs but generates hex strings
    val sessionToken = "Bearer MIKU_MIKU_OO_EE_OO"

    NavHost(
        navController = navController,
        startDestination = NavRoute.Main.route
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

        // Main screen — has top/bottom bars
        composable(NavRoute.Main.route) {
            MainScaffold(
                onSearchClick = {
                    navController.navigate(NavRoute.Search.route)
                }
            )
        }

        // Search screen — full screen, NO top/bottom bars
        composable(NavRoute.Search.route) {
            SearchScreen(
                viewModel = searchViewModel,
                token = sessionToken,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
