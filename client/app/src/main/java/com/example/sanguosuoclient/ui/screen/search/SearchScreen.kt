package com.example.sanguosuoclient.ui.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.data.session.SessionManager
import com.example.sanguosuoclient.ui.components.SanguosuoSearchBar

@Composable
fun SearchScreenRoute(
    onBack: () -> Unit,
    onHeroClick: (Hero) -> Unit,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        query = query,
        uiState = uiState,
        onBack = onBack,
        onHeroClick = onHeroClick,
        onQueryChange = viewModel::onQueryChange,
        onSearch = viewModel::onSearch,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun SearchScreen(
    query: String,
    uiState: SearchUiState,
    onBack: () -> Unit,
    onHeroClick: (Hero) -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            SanguosuoSearchBar(
                query = query,
                onQueryChange = onQueryChange,
                enabled = true,
                onSearch = onSearch,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = uiState) {
            is SearchUiState.Idle -> {
                Text(
                    text = "Enter a hero name to search...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            is SearchUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is SearchUiState.Success -> {
                SearchResults(
                    heroes = state.heroes,
                    resultCount = state.heroes.size,
                    onHeroClick = onHeroClick
                )
            }

            is SearchUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun SearchResults(heroes: List<Hero>, resultCount: Int, onHeroClick: (Hero) -> Unit) {
    LazyColumn {
        // Heroes section header with result count
        item {
            Text(
                text = if (resultCount > 0) {
                    "Heroes ($resultCount ${if (resultCount == 1) "result" else "results"})"
                } else {
                    "Heroes"
                },
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        if (heroes.isEmpty()) {
            item {
                Text(
                    text = "No hero found",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
            }
        }
        else {
            items(heroes, key = { it.id }) { hero ->
                SearchResultRow(
                    hero = hero,
                    onClick = { onHeroClick(hero) }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            }
        }
    }
}

@Composable
private fun SearchResultRow(hero: Hero, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp)
    ) {
        // Search icon bên trái
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )

        // Tên hero (chiếm phần còn lại)
        Text(
            text = hero.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        )

        // Ảnh hero bên phải — map hero.id (VD: "WEI001") → drawable "wei_001"
        val context = LocalContext.current
        val drawableName = remember(hero.id) {
            val prefix = hero.id.dropLast(3).lowercase()
            val number = hero.id.takeLast(3)
            "${prefix}_${number}"
        }
        val drawableId = remember(drawableName) {
            context.resources.getIdentifier(drawableName, "drawable", context.packageName)
        }
        Image(
            painter = if (drawableId != 0) painterResource(drawableId)
                      else painterResource(R.drawable.welcome_screen_background),
            contentDescription = hero.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}
