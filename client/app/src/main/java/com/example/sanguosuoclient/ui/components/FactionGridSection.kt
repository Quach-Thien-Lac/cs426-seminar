package com.example.sanguosuoclient.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.data.model.Faction
import com.example.sanguosuoclient.data.model.FactionData
import com.example.sanguosuoclient.data.model.Hero

@Composable
fun FactionGridSection(
    onHeroClick: (Hero) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFaction by remember { mutableStateOf<Faction?>(null) }

    Column(modifier = modifier) {
        // Section title
        Text(
            text = "The four pillars",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // 2x2 Faction Grid
        FactionGrid(
            selectedFaction = selectedFaction,
            onFactionSelect = { faction ->
                selectedFaction = if (selectedFaction?.code == faction.code) null else faction
            }
        )

        // Hero Panel (animated)
        AnimatedVisibility(
            visible = selectedFaction != null,
            enter = expandVertically(
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(300, delayMillis = 100)),
            exit = shrinkVertically(
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            ) + fadeOut(animationSpec = tween(200))
        ) {
            selectedFaction?.let { faction ->
                FactionHeroPanel(
                    faction = faction,
                    onHeroClick = onHeroClick
                )
            }
        }
    }
}

@Composable
private fun FactionGrid(
    selectedFaction: Faction?,
    onFactionSelect: (Faction) -> Unit,
    modifier: Modifier = Modifier
) {
    val factions = FactionData.allFactions
    val infiniteTransition = rememberInfiniteTransition(label = "factionGrid")

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Row 1: WEI, SHU
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                FactionCard(
                    faction = factions[0],
                    isSelected = selectedFaction?.code == factions[0].code,
                    onClick = { onFactionSelect(factions[0]) },
                    infiniteTransition = infiniteTransition,
                    modifier = Modifier.weight(1f)
                )
                FactionCard(
                    faction = factions[1],
                    isSelected = selectedFaction?.code == factions[1].code,
                    onClick = { onFactionSelect(factions[1]) },
                    infiniteTransition = infiniteTransition,
                    modifier = Modifier.weight(1f)
                )
            }
            // Row 2: WU (DOU), QUN
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                FactionCard(
                    faction = factions[2],
                    isSelected = selectedFaction?.code == factions[2].code,
                    onClick = { onFactionSelect(factions[2]) },
                    infiniteTransition = infiniteTransition,
                    modifier = Modifier.weight(1f)
                )
                FactionCard(
                    faction = factions[3],
                    isSelected = selectedFaction?.code == factions[3].code,
                    onClick = { onFactionSelect(factions[3]) },
                    infiniteTransition = infiniteTransition,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Center emblem (卍-like decorative element)
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerHighest,
            shadowElevation = 4.dp,
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "卍",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun FactionCard(
    faction: Faction,
    isSelected: Boolean,
    onClick: () -> Unit,
    infiniteTransition: InfiniteTransition,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Scale animation on press
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = 0.4f, stiffness = 400f),
        label = "cardScale"
    )

    // Glow border for selected state
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    val borderColor by animateColorAsState(
        targetValue = if (isSelected) faction.color.copy(alpha = glowAlpha) else Color.Transparent,
        animationSpec = tween(300),
        label = "borderColor"
    )

    // Shimmer effect
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = if (isSelected) 8.dp else 2.dp,
        modifier = modifier
            .aspectRatio(1.1f)
            .scale(scale)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            faction.color.copy(alpha = 0.85f),
                            faction.darkColor
                        )
                    )
                )
                .drawWithContent {
                    drawContent()
                    // Shimmer overlay
                    val shimmerWidth = size.width * 0.4f
                    val startX = shimmerOffset * size.width
                    drawRect(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.07f),
                                Color.Transparent
                            ),
                            start = Offset(startX, 0f),
                            end = Offset(startX + shimmerWidth, size.height)
                        )
                    )
                }
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                )
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Faction symbol
                Text(
                    text = faction.symbol,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Faction display name
                Text(
                    text = faction.displayName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Tagline
                Text(
                    text = faction.tagline,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center
                )
            }

            // Hero count badge (top-right)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(
                        Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "${faction.heroCount}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun FactionHeroPanel(
    faction: Faction,
    onHeroClick: (Hero) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        // Section header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            // Colored accent bar
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(20.dp)
                    .background(faction.color, RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Featured Heroes · ${faction.chineseName.uppercase()}",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // Horizontal hero list
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(faction.topHeroes) { hero ->
                FactionHeroCard(
                    hero = hero,
                    factionColor = faction.color,
                    onClick = { onHeroClick(hero) }
                )
            }
        }
    }
}

@Composable
private fun FactionHeroCard(
    hero: Hero,
    factionColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val drawableName = remember(hero.id) {
        val prefix = hero.id.dropLast(3).lowercase()
        val number = hero.id.takeLast(3)
        "${prefix}_${number}"
    }
    val drawableId = remember(drawableName) {
        context.resources.getIdentifier(drawableName, "drawable", context.packageName)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(100.dp)
            .clickable(onClick = onClick)
    ) {
        // Hero image with faction-colored border accent
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp, factionColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
        ) {
            Image(
                painter = if (drawableId != 0) painterResource(drawableId)
                          else painterResource(R.drawable.welcome_screen_background),
                contentDescription = hero.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Faction color dot indicator
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(6.dp)
                    .size(8.dp)
                    .background(factionColor, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Hero name
        Text(
            text = hero.name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Epithet
        hero.epithet?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
