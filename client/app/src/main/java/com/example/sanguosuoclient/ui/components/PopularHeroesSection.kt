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
import com.example.sanguosuoclient.data.model.SkillTag

private val popularHeroes = listOf(
    Hero(
        id = "WEI015",
        name = "Từ Hoảng",
        imageUrl = "https://static.wikia.nocookie.net/tam-quoc-sat/images/e/e8/Thm.jpg/revision/latest?cb=20220511095938&path-prefix=vi",
        factionCode = "WEI",
        factionName = "Nguỵ",
        hp = 2.0f,
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
        id = "QUN003",
        name = "Giả Hủ",
        imageUrl = "https://static.wikia.nocookie.net/tam-quoc-sat/images/f/fa/M%C6%B0u_gi%E1%BA%A3_h%E1%BB%A7_m.jpg/revision/latest?cb=20240913100604&path-prefix=vi",
        factionCode = "QUN",
        factionName = "Quần",
        hp = 1.5f,
        epithet = "Lãnh Khốc Độc Sĩ",
        quote = "Với tất cả kế pháp của tại hạ, há sợ thiên hạ không loạn!",
        hasTradeoff = false,
        skills = listOf(
            HeroSkill(
                skillId = "QUN003_1",
                skillTags = listOf(SkillTag("HDK", "Hạn Định Kỹ")),
                skillName = "Loạn Vũ",
                skillDescription = "Giai đoạn hành động, bạn lệnh cho tất cả người khác sử dụng thẻ [Sát] đối với mục tiêu là người chơi được tính khoảng cách nhỏ nhất, nếu không họ phải tự mất 1 sinh lực."
            ),
            HeroSkill(
                skillId = "QUN003_2",
                skillTags = listOf(SkillTag("TDK", "Toả Định Kỹ")),
                skillName = "Hoàn Sát",
                skillDescription = "Trong lượt của bạn, khi có người chơi rơi vào trạng thái hấp hối, chỉ bạn và người đó mới có thể dùng [Đào]."
            ),
            HeroSkill(
                skillId = "QUN003_3",
                skillTags = listOf(SkillTag("TDK", "Toả Định Kỹ")),
                skillName = "Duy Mạc",
                skillDescription = "Khi bạn trở thành mục tiêu của Câm Nang sắc Đen, hủy bỏ nó."
            )
        )
    ),
    Hero(
        id = "WEI001",
        name = "Tào Tháo",
        imageUrl = "https://static.wikia.nocookie.net/tam-quoc-sat/images/4/49/T%C3%A0o_th%C3%A1o.jpg/revision/latest?cb=20230320111754&path-prefix=vi",
        factionCode = "WEI",
        factionName = "Nguỵ",
        hp = 2.0f,
        epithet = "Tuyệt Thế Đích Gian Hùng",
        quote = "Thà ta phụ người trong thiên hạ chứ không để người trong thiên hạ phụ ta",
        hasTradeoff = false,
        skills = listOf(
            HeroSkill(
                skillId = "WEI001_1",
                skillTags = emptyList(),
                skillName = "Gian Hùng",
                skillDescription = "Sau khi bạn nhận sát thương, bạn có thể thu lấy thẻ bài gây sát thương cho bạn lên tay hoặc chọn rút 1 lá bài."
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
