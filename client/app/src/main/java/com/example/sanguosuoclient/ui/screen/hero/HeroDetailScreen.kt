package com.example.sanguosuoclient.ui.screen.hero

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

private val SkillRowHeight = 140.dp
private val PortraitHeight = SkillRowHeight * 2

@Composable
fun HeroDetailScreenRoute(
    heroId: String,
    viewModel: HeroDetailViewModel = viewModel(factory = HeroDetailViewModel.Factory)
) {
    val sessionToken = "Bearer MIKU_MIKU_OO_EE_OO"
    val uiState by viewModel.heroDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(heroId) {
        viewModel.fetchHero(sessionToken, heroId)
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
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun HeroDetailScreen(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        HeroHeader(hero)

        Spacer(modifier = Modifier.height(20.dp))

        HeroInformationCard(hero,)

        Spacer(modifier = Modifier.height(20.dp))
    }

}

// hero banner with hero image, name, epithet and quote
@Composable
private fun HeroBanner(
    hero: hero,
    modifier: Modifier = Modifier
) {
    //  changed layout from Column to Box to allow the hero name and epithet to be on top of the image
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(hero.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = hero.name,
                    contentScale = ContentScale.Fit,
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(PortraitHeight)
                )
        // this box is to add a black gradient overlay on the image 
        Box(
            modifier  = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = 350f,
                    endY = 450f
                )
            )
        )
        
        // this column is to add the hero name and epithet on top of the image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.BottomStart),
                    
        ) {
            Text(
            text = hero.name,
            style = MaterialTheme.typography.titleSmall,
        )

        hero.epithet?.let { epithet ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = epithet,
                style = MaterialTheme.typography.titleSmall,
                color = errorLight,
                fontSize = 20.sp
            )
        }

        hero.quote?.let { quote ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "\"$quote\"",
                style = MaterialTheme.typography.titleSmall,
                color = inversePrimaryLightMediumContrast,
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
        }
    }
}

// hero faction and difficulty in a horizontal row, grid-style
@Composable
private fun HeroOverviewRow(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    
}

@Composable
private fun HeroHeader(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hero.name,
            style = MaterialTheme.typography.titleSmall,
        )

        hero.epithet?.let { epithet ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = epithet,
                style = MaterialTheme.typography.titleSmall,
                color = errorLight,
                fontSize = 20.sp
            )
        }

        hero.quote?.let { quote ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "\"$quote\"",
                style = MaterialTheme.typography.titleSmall,
                color = inversePrimaryLightMediumContrast,
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun HeroInformationCard(
    hero: Hero,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, color = Color.Red), shape = RoundedCornerShape(4.dp))
    ) {
        // "Information" banner
        Surface(
            color = inversePrimaryLightMediumContrast,
            shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Information",
                fontSize = 20.sp,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }

        // Fixed-size table: left column height = PortraitHeight + 1 row (faction).
        // Right column height = SKILL_SLOT_COUNT rows. Both totals are equal by
        // construction (PortraitHeight = 2 rows), so nothing needs to flex.
        Row {
            Column(modifier = Modifier.weight(1f)) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(hero.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = hero.name,
                    contentScale = ContentScale.Fit,
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(PortraitHeight)
                )

                HorizontalDividerLine()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(SkillRowHeight)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = hero.factionCode,
                        style = MaterialTheme.typography.displayMedium,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = hero.factionName,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 32.sp
                    )
                }
            }

            VerticalDividerLine()

            Column(modifier = Modifier.weight(1f)) {
                repeat(3) { slot ->
                    SkillCell(
                        skill = hero.skills.getOrNull(slot),
                        index = slot + 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(SkillRowHeight)
                    )
                    if (slot != 3 - 1) {
                        HorizontalDividerLine()
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillCell(
    skill: HeroSkill?,
    index: Int,
    modifier: Modifier = Modifier
) {
    if (skill == null) {
        Box(
            modifier = modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "This hero has no Skill $index",
                style = MaterialTheme.typography.displayMedium,
                color = Color.Gray
            )
        }
        return
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // in case description overflows the fixed row
    ) {
        Text(
            text = "Skill $index - ${skill.skillName}",
            style = MaterialTheme.typography.labelSmall,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = skill.skillDescription,
            style = MaterialTheme.typography.displayMedium,
            fontSize = 10.sp
        )
    }
}


@Composable
private fun VerticalDividerLine() {
    VerticalDivider(
        color = Color.Red,
        thickness = 1.dp
    )
}

@Composable
private fun HorizontalDividerLine() {
    HorizontalDivider(
        color = Color.Red,
        thickness = 1.dp
    )
}


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

