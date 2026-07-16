package com.example.sanguosuoclient.ui.navigation

sealed class NavRoute(val route: String) {
    data object Welcome : NavRoute("welcome")
    data object SignIn : NavRoute("signin")
    data object SignUp : NavRoute("signup")
}
