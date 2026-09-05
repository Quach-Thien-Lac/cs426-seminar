package com.example.sanguosuoclient.data.model

import androidx.compose.ui.graphics.Color

data class Faction(
    val code: String,
    val displayName: String,
    val chineseName: String,
    val tagline: String,
    val color: Color,
    val darkColor: Color,
    val symbol: String,
    val heroCount: Int,
    val topHeroes: List<Hero>
)

object FactionData {

    val Wei = Faction(
        code = "WEI",
        displayName = "WEI",
        chineseName = "Nguỵ",
        tagline = "Nguỵ · Bắc Cường",
        color = Color(0xFF2D4B7A),
        darkColor = Color(0xFF1A2D4A),
        symbol = "⛰",
        heroCount = 50,
        topHeroes = listOf(
            Hero(
                id = "WEI001",
                name = "Tào Tháo",
                factions = listOf(HeroFaction("WEI", "Nguỵ")),
                hp = 2.0f,
                epithet = "Tuyệt Thế Đích Gian Hùng",
                heroComplexity = 1
            ),
            Hero(
                id = "WEI002",
                name = "Tào Phi",
                factions = listOf(HeroFaction("WEI", "Nguỵ")),
                hp = 1.5f,
                epithet = "Bá Nghiệp Đích Kế Thừa Giả",
                heroComplexity = 1
            ),
            Hero(
                id = "WEI003",
                name = "Tư Mã Ý",
                factions = listOf(HeroFaction("WEI", "Nguỵ")),
                hp = 1.5f,
                epithet = "Lang Cố Chi Quỷ",
                heroComplexity = 1
            ),
            Hero(
                id = "WEI004",
                name = "Quách Gia",
                factions = listOf(HeroFaction("WEI", "Nguỵ")),
                hp = 1.5f,
                epithet = "Tảo Chung Tiên Tri",
                heroComplexity = 1
            ),
            Hero(
                id = "WEI005",
                name = "Hứa Chử",
                factions = listOf(HeroFaction("WEI", "Nguỵ")),
                hp = 2.0f,
                epithet = "Hổ Si",
                heroComplexity = 1
            )
        )
    )

    val Shu = Faction(
        code = "SHU",
        displayName = "SHU",
        chineseName = "Thục",
        tagline = "Thục · Nhân Nghĩa",
        color = Color(0xFF2D5A3D),
        darkColor = Color(0xFF1A3D28),
        symbol = "🍃",
        heroCount = 50,
        topHeroes = listOf(
            Hero(
                id = "SHU001",
                name = "Lưu Bị",
                factions = listOf(HeroFaction("SHU", "Thục")),
                hp = 2.0f,
                epithet = "Loạn Thế Đích Kiêu Hùng",
                heroComplexity = 1
            ),
            Hero(
                id = "SHU002",
                name = "Lưu Thiện",
                factions = listOf(HeroFaction("SHU", "Thục")),
                hp = 1.5f,
                epithet = "Vô Vi Đích Chân Mệnh Chủ",
                heroComplexity = 1
            ),
            Hero(
                id = "SHU003",
                name = "Khổng Minh",
                factions = listOf(HeroFaction("SHU", "Thục")),
                hp = 1.5f,
                epithet = "Ngọa Long",
                heroComplexity = 1
            ),
            Hero(
                id = "SHU004",
                name = "Bàng Thống",
                factions = listOf(HeroFaction("SHU", "Thục")),
                hp = 1.5f,
                epithet = "Phượng Sồ",
                heroComplexity = 1
            ),
            Hero(
                id = "SHU005",
                name = "Quan Vũ",
                factions = listOf(HeroFaction("SHU", "Thục")),
                hp = 2.5f,
                epithet = "Trung Nghĩa Đích Võ Thánh",
                heroComplexity = 1
            )
        )
    )

    val Wu = Faction(
        code = "WU",
        displayName = "DOU",
        chineseName = "Đông Ngô",
        tagline = "Đông Ngô · Thủy Chiến",
        color = Color(0xFF7A2D2D),
        darkColor = Color(0xFF4A1A1A),
        symbol = "🌊",
        heroCount = 50,
        topHeroes = listOf(
            Hero(
                id = "WU001",
                name = "Tôn Quyền",
                factions = listOf(HeroFaction("WU", "Ngô")),
                hp = 2.0f,
                epithet = "Niên Khinh Đích Hiền Quân",
                heroComplexity = 1
            ),
            Hero(
                id = "WU002",
                name = "Tôn Kiên",
                factions = listOf(HeroFaction("WU", "Ngô")),
                hp = 2.5f,
                epithet = "Vũ Liệt Đế",
                heroComplexity = 1
            ),
            Hero(
                id = "WU003",
                name = "Chu Du",
                factions = listOf(HeroFaction("WU", "Ngô")),
                hp = 1.5f,
                epithet = "Đại Đô Đốc",
                heroComplexity = 1
            ),
            Hero(
                id = "WU004",
                name = "Lỗ Túc",
                factions = listOf(HeroFaction("WU", "Ngô")),
                hp = 1.5f,
                epithet = "Độc Đoán Đích Ngoại Giao Gia",
                heroComplexity = 1
            ),
            Hero(
                id = "WU005",
                name = "Chu Thái",
                factions = listOf(HeroFaction("WU", "Ngô")),
                hp = 2.0f,
                epithet = "Lịch Chiến Chi Khu",
                heroComplexity = 1
            )
        )
    )

    val Qun = Faction(
        code = "QUN",
        displayName = "QUN",
        chineseName = "Quần Hùng",
        tagline = "Quần Hùng · Độc Lập",
        color = Color(0xFF4A2D7A),
        darkColor = Color(0xFF2D1A4A),
        symbol = "✦",
        heroCount = 50,
        topHeroes = listOf(
            Hero(
                id = "QUN001",
                name = "Trương Giác",
                factions = listOf(HeroFaction("QUN", "Quần")),
                hp = 1.5f,
                epithet = "Thiên Công Tướng Quân",
                heroComplexity = 1
            ),
            Hero(
                id = "QUN002",
                name = "Viên Thiệu",
                factions = listOf(HeroFaction("QUN", "Quần")),
                hp = 2.0f,
                epithet = "Cao Quý Đích Danh Môn",
                heroComplexity = 1
            ),
            Hero(
                id = "QUN003",
                name = "Giả Hủ",
                factions = listOf(HeroFaction("QUN", "Quần")),
                hp = 1.5f,
                epithet = "Lãnh Khốc Độc Sĩ",
                heroComplexity = 1
            ),
            Hero(
                id = "QUN004",
                name = "Thái Văn Cơ",
                factions = listOf(HeroFaction("QUN", "Quần")),
                hp = 1.5f,
                epithet = "Di Hương Cô Nữ",
                heroComplexity = 1
            ),
            Hero(
                id = "QUN005",
                name = "Nhan Lương & Văn Xú",
                factions = listOf(HeroFaction("QUN", "Quần")),
                hp = 2.0f,
                epithet = "Hổ Lâng Huynh Đệ",
                heroComplexity = 1
            )
        )
    )

    val allFactions = listOf(Wei, Shu, Wu, Qun)
}
