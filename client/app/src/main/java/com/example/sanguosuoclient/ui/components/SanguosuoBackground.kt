package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.R

@Composable
fun SanguosuoBackground(
    modifier: Modifier = Modifier,
    isWhiteTinted: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painterResource(R.drawable.welcome_screen_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = (if (isWhiteTinted) 0.2f else 1.0f)
        )
        content()
    }
}

@Preview
@Composable
fun BackgroundPreview() {
    SanguosuoBackground(isWhiteTinted = true) {

    }
}