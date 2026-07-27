package com.example.sanguosuoclient.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sanguosuoclient.ui.MainScaffold
import com.example.sanguosuoclient.ui.screen.welcome.WelcomeScreen
import com.example.sanguosuoclient.ui.screen.signin.SignInScreen
import com.example.sanguosuoclient.ui.screen.signin.SignInScreenRoute
import com.example.sanguosuoclient.ui.screen.signup.SignUpScreenRoute

@Composable
fun AppNavHost(
    navController: NavHostController
) {
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
            SignInScreenRoute (
                onNavigateToSignUp = {
                    navController.navigate(NavRoute.SignUp.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                onSubmit = {
                    navController.navigate(NavRoute.Main.route) {
                        // clear the whole auth stack so back button doesn't return to it
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
                onSubmit = {

                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoute.Main.route) {
            MainScaffold()
        }
    }
}
