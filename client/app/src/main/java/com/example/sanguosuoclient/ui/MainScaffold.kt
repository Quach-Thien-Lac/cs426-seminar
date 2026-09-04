package com.example.sanguosuoclient.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sanguosuoclient.ui.components.SanguosuoBottomBar
import com.example.sanguosuoclient.ui.components.SanguosuoTopBar
import com.example.sanguosuoclient.ui.navigation.MainGraph
import com.example.sanguosuoclient.ui.navigation.MainRoute
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailScreenRoute
import com.example.sanguosuoclient.ui.screen.home.HomeScreen
import com.example.sanguosuoclient.ui.screen.search.SearchScreenRoute

@Composable
fun MainScaffold(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val currentGraphRoute = backStackEntry?.destination?.hierarchy
        ?.firstOrNull {it.route in MainGraph.all}
        ?.route
        ?: MainGraph.HomeGraph.route

    Scaffold(
        topBar = {
            if (currentRoute != MainRoute.Search.route) {
                SanguosuoTopBar(
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        bottomBar = {
            SanguosuoBottomBar(
                currentGraphRoute = currentGraphRoute,
                onNavigate = { graphRoute ->
                    if (currentGraphRoute == graphRoute) {
                        navController.navigate(graphRoute) {
                            popUpTo(graphRoute) {
                                inclusive = true
                            }
                        }
                    }
                    else {
                        navController.navigate(graphRoute) {
                            launchSingleTop = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainGraph.HomeGraph.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(
                startDestination = MainRoute.Home.route,
                route = MainGraph.HomeGraph.route,
            ) {
                composable(MainRoute.Home.route) {
                    HomeScreen(
                        onHeroClick = { hero ->
                            navController.navigate("home/${MainRoute.HeroDetail.createRoute(hero.id)}")
                        }
                    )
                }

                composable(
                    route = "home/${MainRoute.HeroDetail.route}",
                    arguments = listOf(navArgument("heroId") {type = NavType.StringType})
                ) { backStackEntry ->
                    val heroId = backStackEntry.arguments?.getString("heroId") ?: return@composable
                    HeroDetailScreenRoute(heroId = heroId)
                }
            }

            navigation(
                startDestination = MainRoute.Search.route,
                route = MainGraph.SearchGraph.route,
            ) {
                composable(MainRoute.Search.route) {
                    SearchScreenRoute(
                        onBack = { navController.popBackStack() },
                        onHeroClick = { hero ->
                            navController.navigate("search/${MainRoute.HeroDetail.createRoute(hero.id)}")
                        }
                    )
                }

                composable(
                    route = "search/${MainRoute.HeroDetail.route}",
                    arguments = listOf(navArgument("heroId") {type = NavType.StringType})
                ) { backStackEntry ->
                    val heroId = backStackEntry.arguments?.getString("heroId") ?: return@composable
                    HeroDetailScreenRoute(heroId = heroId)
                }
            }

            navigation(
                startDestination = MainRoute.Saved.route,
                route = MainGraph.SavedGraph.route
            ) {
                composable(MainRoute.Saved.route) {
                    Text("Saved Screen Placeholder")
                }
            }

            navigation(
                startDestination = MainRoute.Profile.route,
                route = MainGraph.ProfileGraph.route,
            ) {
                composable(MainRoute.Profile.route) {
                    Text("Profile Screen Placeholder")
                }
            }
        }
    }
}

