package com.example.sanguosuoclient.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
        startDestination = NavRoute.Welcome.route
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
                onSubmit = {}
            )
        }

        composable(NavRoute.SignUp.route) {
            SignUpScreenRoute(
                onSubmit = {},
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
