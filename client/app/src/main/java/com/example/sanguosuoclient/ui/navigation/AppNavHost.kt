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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.compose.ui.platform.LocalContext
import com.example.sanguosuoclient.SanguosuoApplication
import com.example.sanguosuoclient.ui.MainScaffold
import com.example.sanguosuoclient.ui.components.SanguosuoBottomBar
import com.example.sanguosuoclient.ui.components.SanguosuoTopBar
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailScreen
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailUiState
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailViewModel
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

    val sessionToken = "Bearer MIKU_MIKU_OO_EE_OO"

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
                onSearchClick = {
                    navController.navigate(NavRoute.Search.route)
                },
                onHeroClick = { hero ->
                    navController.navigate(NavRoute.HeroDetail.createRoute(hero.id))
                }
            )
        }

        // Search screen — full screen, NO top/bottom bars
        composable(NavRoute.Search.route) {
            SearchScreen(
                viewModel = searchViewModel,
                token = sessionToken,
                onBack = { navController.popBackStack() },
                onHeroClick = { hero ->
                    navController.navigate(NavRoute.HeroDetail.createRoute(hero.id))
                }
            )
        }

        // Hero detail screen — with top bar (back + search) and bottom bar
        composable(
            route = NavRoute.HeroDetail.route,
            arguments = listOf(navArgument("heroId") { type = NavType.StringType })
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId") ?: return@composable
            val heroDetailViewModel: HeroDetailViewModel = viewModel(
                factory = HeroDetailViewModel.Factory
            )
            val uiState by heroDetailViewModel.uiState.collectAsState()

            LaunchedEffect(heroId) {
                heroDetailViewModel.fetchHero(sessionToken, heroId)
            }

            Scaffold(
                topBar = {
                    SanguosuoTopBar(
                        onSearchClick = { navController.navigate(NavRoute.Search.route) },
                        onBackClick = { navController.popBackStack() }
                    )
                },
                bottomBar = {
                    SanguosuoBottomBar(
                        currentRoute = "",
                        onNavigate = {
                            navController.navigate(NavRoute.Main.route) {
                                popUpTo(NavRoute.Main.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    when (val state = uiState) {
                        is HeroDetailUiState.Idle -> { /* blank */ }
                        is HeroDetailUiState.Loading -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                        is HeroDetailUiState.Success -> {
                            HeroDetailScreen(
                                hero = state.hero,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        is HeroDetailUiState.Error -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(text = state.message)
                            }
                        }
                    }
                }
            }
        }
    }
}
