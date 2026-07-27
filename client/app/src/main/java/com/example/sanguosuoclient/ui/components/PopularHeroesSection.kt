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
import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.data.model.HeroSkill

private val popularHeroes = listOf(
    Hero(
        id = "WEI015",
        name = "Từ Hoảng",
        imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/pacific-chorus-frog.png",
        factionCode = "WEI",
        factionName = "Nguỵ",
        hp = 2,
        epithet = "Chu Á Chi Phong",
        quote = "Thanh Đông kích Tây, thiêu kỳ lương thảo!",
        hasTradeoff = false,
        skills = listOf(
            HeroSkill(
                skillId = "WEI015_1",
                skillTags = emptyList(),
                skillName = "Đoạn Lương",
                skillDescription = "Giai đoạn hành động, bạn có thể sử dụng thẻ bài Phi Cẩm Nang sắc Đen xem như 1 thẻ [Binh Lương Thốn Đoạn] không hạn chế khoảng cách. Nếu bạn vừa sử dụng thẻ [Binh Lương Thốn Đoạn] đối với 1 người chới trong khoảng cách vượt quá 2, bạn không thể tái phát động kỹ năng cho đến hết lượt."
            )
        )
    ),
    Hero(
        id = "WEI015",
        name = "Từ Hoaang",
        imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/pacific-chorus-frog.png",
        factionCode = "WEI",
        factionName = "Nguỵ",
        hp = 2,
        epithet = "Chu Á Chi Phong",
        quote = "Thanh Đông kích Tây, thiêu kỳ lương thảo!",
        hasTradeoff = false,
        skills = listOf(
            HeroSkill(
                skillId = "WEI015_1",
                skillTags = emptyList(),
                skillName = "Đoạn Lương",
                skillDescription = "Giai đoạn hành động, bạn có thể sử dụng thẻ bài Phi Cẩm Nang sắc Đen xem như 1 thẻ [Binh Lương Thốn Đoạn] không hạn chế khoảng cách. Nếu bạn vừa sử dụng thẻ [Binh Lương Thốn Đoạn] đối với 1 người chới trong khoảng cách vượt quá 2, bạn không thể tái phát động kỹ năng cho đến hết lượt."
            ),
            HeroSkill(
                skillId = "WEI015_2",
                skillTags = emptyList(),
                skillName = "Đoạn Lương",
                skillDescription = "Giai đoạn hành động, bạn có thể sử dụng thẻ bài Phi Cẩm Nang sắc Đen xem như 1 thẻ [Binh Lương Thốn Đoạn] không hạn chế khoảng cách. Nếu bạn vừa sử dụng thẻ [Binh Lương Thốn Đoạn] đối với 1 người chới trong khoảng cách vượt quá 2, bạn không thể tái phát động kỹ năng cho đến hết lượt."
            )
        )
    )
)

@Composable
fun PopularHeroesSection(
    onHeroClick: (Hero) -> Unit,
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
                    onClick = { onHeroClick(hero) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
