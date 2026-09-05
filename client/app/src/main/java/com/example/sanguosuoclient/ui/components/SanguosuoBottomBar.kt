package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.navigation.MainGraph
import com.example.sanguosuoclient.ui.navigation.MainRoute
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme

data class BottomNavItem(
    val title: String,
    val iconId: Int,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.ic_home, MainGraph.HomeGraph.route),
    BottomNavItem("Search", R.drawable.ic_search, MainGraph.SearchGraph.route),
    BottomNavItem("Saved", R.drawable.ic_bookmark, MainGraph.SavedGraph.route),
    BottomNavItem("Profile", R.drawable.ic_account, MainGraph.ProfileGraph.route)
)

@Composable
fun SanguosuoBottomBar(
    currentGraphRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HorizontalDivider(thickness = 1.dp, color = Color.Black)

        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 0.dp,
        ) {
            bottomNavItems.forEach { item ->
                val selected = currentGraphRoute == item.route

                NavigationBarItem(
                    selected = selected,
                    onClick = { onNavigate(item.route) },
                    icon = {
                        Icon(
                            painter = painterResource(item.iconId),
                            contentDescription = item.title,
                            modifier = Modifier.size(36.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    SanguosuoClientTheme() {
        SanguosuoBottomBar(
            currentGraphRoute = MainRoute.Home.route,
            onNavigate = {},
            modifier = Modifier.width(300.dp).height(75.dp)
        )
    }
}