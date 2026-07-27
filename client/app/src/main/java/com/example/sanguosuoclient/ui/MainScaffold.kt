package com.example.sanguosuoclient.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.data.model.HeroSkill
import com.example.sanguosuoclient.ui.components.SanguosuoBottomBar
import com.example.sanguosuoclient.ui.components.SanguosuoTopBar
import com.example.sanguosuoclient.ui.screen.hero.HeroDetailScreen
import com.example.sanguosuoclient.ui.screen.home.HomeScreen

@Composable
fun MainScaffold(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            SanguosuoTopBar(
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar = {
            SanguosuoBottomBar(
                currentRoute = currentRoute ?: "undefined",
                onNavigate = { route ->
                    navController.navigate(route) {
                        // avoid piling up duplicate destinations on repeated taps
                        launchSingleTop = true
                        // pop back to start destination when reselecting a tab
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
            startDestination = "hero",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home_route") {
                HomeScreen()
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
            composable("hero") {
                HeroDetailScreen(mockHeroTuHoang)
            }
        }
    }
}

val mockHeroTuHoang = Hero(
    id = "WEI015",
    name = "Từ Hoảng",
    imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/pacific-chorus-frog.png",
    factionCode = "WEI",
    factionName = "Nguỵ",
    hp = 2,
    epithet = "Chu Á Chi Phong",
    quote = "Thanh Đông kích Tây, thiêu kỳ lương thảo!",
    hasTradeoff = false,
    skills = listOf(
        HeroSkill(
            skillId = "WEI015_1",
            skillTags = emptyList(),
            skillName = "Đoạn Lương",
            skillDescription = "Giai đoạn hành động, bạn có thể sử dụng thẻ bài Phi Cẩm Nang sắc Đen xem như 1 thẻ [Binh Lương Thốn Đoạn] không hạn chế khoảng cách. Nếu bạn vừa sử dụng thẻ [Binh Lương Thốn Đoạn] đối với 1 người chới trong khoảng cách vượt quá 2, bạn không thể tái phát động kỹ năng cho đến hết lượt."
        )
    )
)