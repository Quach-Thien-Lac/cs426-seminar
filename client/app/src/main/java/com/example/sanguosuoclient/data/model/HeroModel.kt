package com.example.sanguosuoclient.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HeroSkill(
    val skillId: String,
    val skillTags: List<String> = emptyList(),
    val skillName: String,
    val skillDescription: String
)

@Serializable
data class Hero(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val factionCode: String,
    val factionName: String,
    val hp: Int,
    val epithet: String,
    val quote: String,
    val hasTradeoff: Boolean = false,
    val skills: List<HeroSkill> = emptyList()
)

// ── GET hero by id → single hero (hero detail screen) ──
@Serializable
data class HeroDetailPayload(
    val message: String,
    val data: Hero
)

// ── GET hero by name → list of heroes (search screen) ──
@Serializable
data class HeroListPayload(
    val message: String,
    val data: List<Hero>
)