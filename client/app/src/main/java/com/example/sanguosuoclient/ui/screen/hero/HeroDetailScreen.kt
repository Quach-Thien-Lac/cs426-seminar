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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
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

val GoldAccent = Color(0xFFDFA437)

private val SkillRowHeight = 140.dp
private val PortraitHeight = SkillRowHeight * 2

@Composable
fun HeroDetailScreenRoute(
    heroId: String,
    viewModel: HeroDetailViewModel = viewModel(factory = HeroDetailViewModel.Factory)
) {
    val uiState by viewModel.heroDetailUiState.collectAsStateWithLifecycle()

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
        HeroBanner(hero = hero)
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
    Row(
        modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Surface(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, color = Color(0xFFDFA437)),
            color = Color.White
                    
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Phe phái",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFDFA437)
                )
                Text(
                    //text = hero.factionName,
                    text = hero.factions[0].factionName,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
        Surface(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, color = Color(0xFFDFA437)),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Độ khó",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFDFA437)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(5) { index ->
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (hero.heroComplexity >= index) GoldAccent else GoldAccent.copy(alpha = 0.3f))
                        )
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

