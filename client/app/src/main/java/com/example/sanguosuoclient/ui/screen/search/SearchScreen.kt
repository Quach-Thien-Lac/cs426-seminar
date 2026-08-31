package com.example.sanguosuoclient.ui.screen.search

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.ui.components.SanguosuoSearchBar

@Composable
fun SearchScreenRoute(
    onBack: () -> Unit,
    onHeroClick: (Hero) -> Unit,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
) {
    val sessionToken = "Bearer MIKU_MIKU_OO_EE_OO"

    val query by viewModel.query.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        query = query,
        uiState = uiState,
        onBack = onBack,
        onHeroClick = onHeroClick,
        onQueryChange = viewModel::onQueryChange,
        onSearch = { viewModel.onSearch(sessionToken) },
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
                    text = stringResource(R.string.initial_text_in_search_screen),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            is SearchUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is SearchUiState.Success -> {
                SearchResults(heroes = state.heroes, onHeroClick = onHeroClick)
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
private fun SearchResults(heroes: List<Hero>, onHeroClick: (Hero) -> Unit) {
    LazyColumn {
        // Heroes section
        item {
            Text(
                text = "Heroes",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        if (heroes.isEmpty()) {
            item {
                Text(
                    text = "No heroes found",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
            }
        }
        else {
            items(heroes) { hero ->
                SearchResultRow(
                    label = hero.name,
                    onClick = { onHeroClick(hero) }
                )
            }
        }
    }
}

@Composable
private fun SearchResultRow(label: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}
