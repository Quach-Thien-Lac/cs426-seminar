package com.example.sanguosuoclient.ui.theme

import android.provider.DocumentsContract
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sanguosuoclient.R

val MaShanZheng = FontFamily(
    Font(R.font.ma_shan_sheng_regular, FontWeight.Normal)
)

val LiuJianMaoCao = FontFamily(
    Font(R.font.liu_jian_mao_cao_regular, FontWeight.Normal)
)

val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_extrabold, FontWeight.ExtraBold),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_light, FontWeight.Light)
)

val Pattaya = FontFamily(
    Font(R.font.pattaya_regular, FontWeight.Normal)
)

val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = MaShanZheng,
        fontWeight = FontWeight.Normal,
        fontSize = 110.sp
    ),
    titleMedium = TextStyle(
        fontFamily = LiuJianMaoCao,
        fontWeight = FontWeight.Normal,
        fontSize = 83.sp,
        color = Color(0xFF6F0000)
    ),
    titleSmall = TextStyle(
        fontFamily = Pattaya,
        fontWeight = FontWeight.Normal,
        fontSize = 43.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    )
)