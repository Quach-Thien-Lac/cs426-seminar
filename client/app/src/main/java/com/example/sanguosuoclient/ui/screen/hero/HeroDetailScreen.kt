package com.example.sanguosuoclient.ui.screen.hero

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sanguosuoclient.ui.theme.Roboto
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.data.model.HeroSkill
import com.example.sanguosuoclient.ui.theme.errorLight
import com.example.sanguosuoclient.ui.theme.inversePrimaryLightMediumContrast
import com.example.sanguosuoclient.ui.theme.primaryLight

val GoldAccent = Color(0xFFDFA437)

private val SkillRowHeight = 140.dp
private val PortraitHeight = SkillRowHeight * 2

@Composable
fun HeroDetailScreenRoute(
    heroId: String,
    viewModel: HeroDetailViewModel = viewModel(factory = HeroDetailViewModel.Factory)
) {
    val uiState by viewModel.heroDetailUiState.collectAsStateWithLifecycle()
    val isSaved by viewModel.isSaved.collectAsStateWithLifecycle()

    LaunchedEffect(heroId) {
        viewModel.fetchHero(heroId)
    }

    when(val state = uiState) {
        is HeroDetailUiState.Idle -> {}

        is HeroDetailUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = primaryLight)
            }
        }

        is HeroDetailUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = state.message)
            }
        }

        is HeroDetailUiState.Success -> {
            HeroDetailScreen(
                hero = state.hero,
                isSaved = isSaved,
                onToggleSave = viewModel::toggleSave,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun HeroDetailScreen(
    hero: Hero,
    isSaved: Boolean = false,
    onToggleSave: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        HeroBanner(
            hero = hero,
            isSaved = isSaved,
            onToggleSave = onToggleSave
        )
        Spacer(modifier = Modifier.height(16.dp))
        HeroOverviewRow(hero = hero)
        Spacer(modifier = Modifier.height(16.dp))
        HeroSkillRow(hero = hero)
    }

}

// hero banner with hero image, name, epithet and quote
@Composable
private fun HeroBanner(
    hero: Hero,
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Map hero.id (VD: "QUN008") -> drawable "qun_008"
    val localDrawableId = remember(hero.id) {
        val prefix = hero.id.dropLast(3).lowercase()
        val number = hero.id.takeLast(3)
        val resName = "${prefix}_${number}"
        val id = context.resources.getIdentifier(resName, "drawable", context.packageName)
        if (id != 0) id else null
    }

    // Loại bỏ dấu ngoặc kép thừa nếu trong database đã có
    val cleanQuote = remember(hero.quote) {
        hero.quote?.trim()?.removeSurrounding("\"")?.removeSurrounding("“", "”")
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(420.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        // Ảnh tướng: ưu tiên imageUrl từ server, fallback về ảnh local drawable
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(if (!hero.imageUrl.isNullOrBlank()) hero.imageUrl else (localDrawableId ?: R.drawable.welcome_screen_background))
                .crossfade(true)
                .build(),
            contentDescription = hero.name,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            placeholder = painterResource(R.drawable.loading_img),
            error = localDrawableId?.let { painterResource(it) } ?: painterResource(R.drawable.ic_broken_image),
            modifier = Modifier.fillMaxSize()
        )

        // Gradient overlay chuyển mượt mà ở nửa dưới của banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.85f),
                            Color.Black.copy(alpha = 0.95f)
                        )
                    )
                )
        )

        // Nút Lưu tướng ở góc trên bên trái (Phong cách Glassmorphism đối xứng với HeroHpBadge)
        HeroSaveButton(
            isSaved = isSaved,
            onToggleSave = onToggleSave,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(14.dp)
        )

        // Badge HP ở góc trên bên phải (Ý tưởng Ngọc Bội Âm Dương Glassmorphism)
        HeroHpBadge(
            hp = hero.hp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(14.dp)
        )

        // Khối hiển thị Danh hiệu, Tên tướng và Quote căn ở đáy
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            hero.epithet?.let { epithet ->
                Text(
                    text = epithet.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldAccent,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
            }

            Text(
                text = hero.name,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            if (!cleanQuote.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(3.dp)
                            .height(26.dp)
                            .background(GoldAccent, shape = RoundedCornerShape(2.dp))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "“$cleanQuote”",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontStyle = FontStyle.Italic,
                            fontFamily = Roboto
                        ),
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

// Nút Lưu tướng theo phong cách Glassmorphism đồng bộ với HeroHpBadge
@Composable
private fun HeroSaveButton(
    isSaved: Boolean,
    onToggleSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onToggleSave,
        modifier = modifier.size(36.dp),
        shape = CircleShape,
        color = Color.Black.copy(alpha = 0.55f),
        border = BorderStroke(1.dp, GoldAccent.copy(alpha = 0.8f)),
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = if (isSaved) "Bỏ lưu tướng" else "Lưu tướng",
                tint = if (isSaved) GoldAccent else Color.White.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// Huy hiệu hiển thị Sinh lực (HP) theo phong cách Ngọc Bội Âm Dương (Glassmorphism)
@Composable
private fun HeroHpBadge(
    hp: Float,
    modifier: Modifier = Modifier
) {
    val fullOrbs = hp.toInt()
    val hasHalfOrb = (hp - fullOrbs) > 0f

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = Color.Black.copy(alpha = 0.55f),
        border = BorderStroke(1.dp, GoldAccent.copy(alpha = 0.8f)),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Dãy biểu tượng Ngọc Sinh Mệnh (Crimson Gem Orbs)
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Các viên ngọc đầy
                repeat(fullOrbs) {
                    Box(
                        modifier = Modifier
                            .size(13.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(Color(0xFFFF5252), Color(0xFFC62828))
                                )
                            )
                            .border(0.8.dp, Color(0xFFFFCDD2).copy(alpha = 0.8f), CircleShape)
                    )
                }

                // Nửa viên ngọc nếu là 1.5 hoặc 2.5
                if (hasHalfOrb) {
                    Box(
                        modifier = Modifier
                            .size(13.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f))
                            .border(0.8.dp, Color(0xFFFFCDD2).copy(alpha = 0.8f), CircleShape)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.5f)
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFFFF5252), Color(0xFFC62828))
                                    )
                                )
                        )
                    }
                }
            }

            // Hiển thị chỉ số HP (VD: 1.5 HP, 2 HP, 2.5 HP)
            Text(
                text = "${if (hp % 1f == 0f) hp.toInt().toString() else hp.toString()} HP",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto
            )
        }
    }
}

// hero faction and difficulty in a horizontal row, grid-style
@Composable
private fun HeroOverviewRow(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Khung Phe phái
        Surface(
            modifier = Modifier
                .weight(1f)
                .height(86.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, color = GoldAccent),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Phe phái",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldAccent
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = hero.factions.firstOrNull()?.factionName ?: "Chưa rõ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

        // Khung Độ khó
        Surface(
            modifier = Modifier
                .weight(1f)
                .height(86.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, color = GoldAccent),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Độ khó",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldAccent
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    val complexity = hero.heroComplexity.coerceIn(1, 5)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) { index ->
                            Box(
                                modifier = Modifier
                                    .width(18.dp)
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(
                                        if (index < complexity) GoldAccent
                                        else GoldAccent.copy(alpha = 0.25f)
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

// hero skill description and skill name, selectable from a horizontal row with skill icons, like in league's wiki
@Composable
private fun HeroSkillRow(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    var selectedSkillIndex by remember { mutableStateOf(0)}
    var selectedSkill = hero.skills.getOrNull(selectedSkillIndex)
    var romanNumerals = listOf("I", "II", "III")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Kỹ năng",
            style = MaterialTheme.typography.labelSmall,
            fontSize = 24.sp,
            color = Color(0xFFDFA437)
        )
        //row of skill icons, selectable
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            hero.skills.forEachIndexed {
                index, _ ->
                val isSelected = index == selectedSkillIndex
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            BorderStroke(1.dp, if (isSelected) Color(0xFFDFA437) else Color(0xFFB0B0B0))
                        )
                        .background(if (isSelected) Color(0xFFDFA437) else Color.White)
                        .clickable {
                            selectedSkillIndex = index
                        },
                    contentAlignment = Alignment.Center,
                    
                ) {
                    Text(
                        text = romanNumerals.getOrNull(index) ?: (index + 1).toString(),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else Color.Black
                    )
                }
            
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // skill name and desc
        if(selectedSkill != null) {
            Text(
                text = selectedSkill.skillName ?: "No skill name",
                style = MaterialTheme.typography.titleSmall,
                fontSize = 24.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = selectedSkill.skillDescription ?: "No skill description",
                style = MaterialTheme.typography.displayMedium,
                fontSize = 16.sp,
                color = Color.Black
            )
        } else {
            Text(
                text = "Default Skill",
                style = MaterialTheme.typography.displayMedium,
                fontSize = 24.sp,
                color = Color.Gray
            )
            Text(
                text = "This skill does nothing",
                style = MaterialTheme.typography.displayMedium,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

// hero story,  just a title with plain text
//@Composable
//private fun HeroBackstory(
//    hero: Hero,
//    modifier: Modifier = Modifier
//) {
//    Text(
//        text = "Tiểu sử",
//        style = MaterialTheme.typography.labelSmall,
//        fontSize = 24.sp,
//        color = Color(0xFFDFA437),
//    )
//    Text(
//        // hardcode for now; update when the backend has a story field
//        text =  "No backstory available",
//        style = MaterialTheme.typography.displayMedium,
//        fontSize = 16.sp,
//        color = Color.Black
//    )
//}

//val mockHeroTuHoang = Hero(
//    id = "WEI015",
//    name = "Từ Hoảng",
//    imageUrl = null,
//    factionCode = "WEI",
//    factionName = "Nguỵ",
//    hp = 2,
//    epithet = "Chu Á Chi Phong",
//    quote = "Thanh Đông kích Tây, thiêu kỳ lương thảo!",
//    hasTradeoff = false,
//    skills = listOf(
//        HeroSkill(
//            skillId = "WEI015_1",
//            skillTags = emptyList(),
//            skillName = "Đoạn Lương",
//            skillDescription = "Giai đoạn hành động, bạn có thể sử dụng thẻ bài Phi Cẩm Nang sắc Đen xem như 1 thẻ [Binh Lương Thốn Đoạn] không hạn chế khoảng cách. Nếu bạn vừa sử dụng thẻ [Binh Lương Thốn Đoạn] đối với 1 người chới trong khoảng cách vượt quá 2, bạn không thể tái phát động kỹ năng cho đến hết lượt."
//        )
//    )
//)

//@Preview (showBackground = true)
//@Composable
//fun HeaderPreview() {
//    SanguosuoClientTheme() {
//        HeroDetailScreen(mockHeroTuHoang)
//    }
//}

