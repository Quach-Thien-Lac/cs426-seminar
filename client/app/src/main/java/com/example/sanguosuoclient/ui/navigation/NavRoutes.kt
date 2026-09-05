package com.example.sanguosuoclient.ui.navigation

sealed class NavRoute(val route: String) {
    data object Welcome : NavRoute("welcome")
    data object SignIn : NavRoute("signin")
    data object SignUp : NavRoute("signup")
    data object Main : NavRoute("main")
}

sealed class MainRoute(val route: String) {
    data object Home : MainRoute("home_route")
    data object Saved : MainRoute("saved_route")
    data object Settings : MainRoute("settings_route")
    data object Profile : MainRoute("profile_route")
    data object Search : MainRoute("search_route")
    data object HeroDetail : MainRoute("hero/{heroId}") {
        fun createRoute(heroId: String) = "hero/$heroId"
    }
}

sealed class MainGraph(val route: String) {
    data object HomeGraph : MainGraph("home_graph")
    data object SearchGraph : MainGraph("search_graph")
    data object SavedGraph : MainGraph("saved_graph")
    data object ProfileGraph : MainGraph("profile_graph")

    companion object {
        val all by lazy {setOf(HomeGraph.route, SearchGraph.route, SavedGraph.route, ProfileGraph.route)}
    }
}