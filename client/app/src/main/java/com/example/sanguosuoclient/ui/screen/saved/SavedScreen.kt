package com.example.sanguosuoclient.ui.screen.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.ui.theme.primaryLight

private val BackgroundCream = Color(0xFFFDF6EC)
private val CardBorder = Color(0xFFE0C9A6)
private val ErrorRed = Color(0xFFC0392B)

@Composable
fun SavedHeroesScreenRoute(
    onHeroClick: (Hero) -> Unit,
    viewModel: SavedHeroesViewModel = viewModel(factory = SavedHeroesViewModel.Factory)
) {
    val uiState by viewModel.savedHeroesUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchSavedHeroes()
    }

    SavedHeroesScreen(
        uiState = uiState,
        onHeroClick = onHeroClick,
        onUnsaveClick = viewModel::unsaveHero,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun SavedHeroesScreen(
    uiState: SavedHeroesUiState,
    onHeroClick: (Hero) -> Unit,
    onUnsaveClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundCream)
    ) {
        when (uiState) {
            is SavedHeroesUiState.Loading, SavedHeroesUiState.Idle -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = primaryLight)
                }
            }

            is SavedHeroesUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.message,
                        color = ErrorRed,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            is SavedHeroesUiState.Success -> {
                if (uiState.heroes.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No saved heroes yet",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(uiState.heroes, key = { it.id }) { hero ->
                            SavedHeroCard(
                                hero = hero,
                                onClick = { onHeroClick(hero) },
                                onUnsaveClick = { onUnsaveClick(hero.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SavedHeroCard(
    hero: Hero,
    onClick: () -> Unit,
    onUnsaveClick: () -> Unit
) {
    val context = LocalContext.current

    val localDrawableId = remember(hero.id) {
        val prefix = hero.id.dropLast(3).lowercase()
        val number = hero.id.takeLast(3)
        val resName = "${prefix}_${number}"
        val id = context.resources.getIdentifier(resName, "drawable", context.packageName)
        if (id != 0) id else null
    }

    Box(
        modifier = Modifier
            .aspectRatio(0.72f)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
    ) {
        // Only this inner box is clickable — the star button below is a
        // SIBLING of this, not a descendant, so there's no ancestor/child
        // clickable ambiguity for touch routing to resolve.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data(if (!hero.imageUrl.isNullOrBlank()) hero.imageUrl else (localDrawableId ?: R.drawable.welcome_screen_background))
                    .crossfade(true)
                    .build(),
                contentDescription = hero.name,
                contentScale = ContentScale.Fit,
                error = localDrawableId?.let { painterResource(it) } ?: painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f))
                        )
                    )
            )

            Text(
                text = hero.name,
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 21.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )

            hero.factions.firstOrNull()?.let { faction ->
                Text(
                    text = faction.factionCode,
                    color = primaryLight,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp)
                        .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }

        // sibling of the clickable box above, not nested inside it
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
                .size(32.dp)
                .background(Color.Black.copy(alpha = 0.35f), CircleShape)
                .clip(CircleShape) // Ensures the ripple and click are perfectly round
                .clickable(onClick = onUnsaveClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Unsave ${hero.name}",
                tint = primaryLight,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}