package com.example.sanguosuoclient.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sanguosuoclient.ui.components.SanguosuoBottomBar
import com.example.sanguosuoclient.ui.components.SanguosuoTopBar
import com.example.sanguosuoclient.ui.navigation.NavRoute
import com.example.sanguosuoclient.ui.screen.herodetail.HeroDetailScreen
import com.example.sanguosuoclient.ui.screen.home.HomeScreen

@Composable
fun MainScaffold(
    onSearchClick: () -> Unit = {},
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            SanguosuoTopBar(
                modifier = Modifier.fillMaxWidth(),
                onSearchClick = onSearchClick
            )
        },
        bottomBar = {
            SanguosuoBottomBar(
                currentRoute = currentRoute ?: "undefined",
                onNavigate = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home_route",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home_route") {
                HomeScreen(
                    onHeroClick = { heroId ->
                        navController.navigate(NavRoute.HeroDetail.createRoute(heroId))
                    }
                )
            }
            composable("saved_route") {
                Text("Saved Screen Placeholder")
            }
            composable("progress_route") {
                Text("Progress Screen Placeholder")
            }
            composable("settings_route") {
                Text("Settings Screen Placeholder")
            }
            composable(
                route = NavRoute.HeroDetail.route,
                arguments = listOf(navArgument("heroId") { type = NavType.StringType })
            ) { backStackEntry ->
                val heroId = backStackEntry.arguments?.getString("heroId").orEmpty()
                HeroDetailScreen(heroId = heroId)
            }
        }
    }
}