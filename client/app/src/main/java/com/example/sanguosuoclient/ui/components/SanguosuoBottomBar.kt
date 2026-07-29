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
import androidx.compose.ui.unit.sp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme

data class BottomNavItem(
    val title: String,
    val iconId: Int,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.ic_home, "home_route"),
    BottomNavItem("Saved", R.drawable.ic_bookmark, "saved_route"),
    BottomNavItem("Progress", R.drawable.ic_check_circle, "progress_route"),
    BottomNavItem("Settings", R.drawable.ic_settings, "settings_route")
)

@Composable
fun SanguosuoBottomBar(
    currentRoute: String,
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
                val selected = currentRoute == item.route

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
            currentRoute = "home_route",
            onNavigate = {},
            modifier = Modifier.width(200.dp).height(50.dp)
        )
    }
}