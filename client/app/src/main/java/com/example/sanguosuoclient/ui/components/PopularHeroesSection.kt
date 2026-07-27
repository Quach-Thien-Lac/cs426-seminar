package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class HeroEntry(val id: String, val name: String, val imageUrl: String? = null)

private val popularHeroes = listOf(
    HeroEntry(id = "cao_cao",   name = "Tào Tháo"),
    HeroEntry(id = "liu_bei",   name = "Lưu Bị"),
    HeroEntry(id = "guan_yu",   name = "Quan Vũ")
)

@Composable
fun PopularHeroesSection(
    onHeroClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Popular heroes",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            popularHeroes.forEach { hero ->
                HeroCard(
                    heroId = hero.id,
                    heroName = hero.name,
                    imageUrl = hero.imageUrl,
                    onClick = { onHeroClick(hero.id) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
