package com.example.sanguosuoclient.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.ui.components.PopularHeroesSection
import com.example.sanguosuoclient.ui.components.TopNewsSection

@Composable
fun HomeScreen(
    onHeroClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        PopularHeroesSection(onHeroClick = onHeroClick)

        Spacer(modifier = Modifier.height(24.dp))

        TopNewsSection()
    }
}