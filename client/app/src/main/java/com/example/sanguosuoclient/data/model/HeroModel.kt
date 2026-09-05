package com.example.sanguosuoclient.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SkillTag (
    val skillTagCode: String,
    val skillTagName: String
)

@Serializable
data class HeroSkill(
    val skillId: String,
    val skillTags: List<SkillTag> = emptyList(),
    val skillName: String,
    val skillDescription: String
)

@Serializable
data class HeroFaction(
    val factionCode: String,
    val factionName: String
)

@Serializable
data class Hero(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val factions: List<HeroFaction> = emptyList(),
    val hp: Float,
    val epithet: String? = null,
    val quote: String? = null,
    val hasTradeoff: Boolean = false,
    val skills: List<HeroSkill> = emptyList()
) {
    // Convenience helpers so existing UI code (HeroDetailScreen, etc.) keeps working
    val factionCode: String get() = factions.firstOrNull()?.factionCode ?: ""
    val factionName: String get() = factions.firstOrNull()?.factionName ?: ""
}

// ── GET hero by name/id → list of heroes (search + detail screen) ──
@Serializable
data class HeroListPayload(
    val message: String,
    val data: List<Hero>
)