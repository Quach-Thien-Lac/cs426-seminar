package com.example.sanguosuoclient.ui.navigation

sealed class NavRoute(val route: String) {
    data object Welcome : NavRoute("welcome")
    data object SignIn : NavRoute("signin")
    data object SignUp : NavRoute("signup")
    data object Main : NavRoute("main")
    data object Search : NavRoute("search")
    data object HeroDetail : NavRoute("hero/{heroId}") {
        fun createRoute(heroId: String) = "hero/$heroId"
    }
}
