package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme

@Composable
fun SanguosuoTopBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SanguosuoSmallTitle()

        Spacer(modifier = Modifier.weight(1f))

        Box(modifier = Modifier.width(160.dp)) {
            SanguosuoSearchBar(
                query = "",
                onQueryChange = {},
                enabled = false,
                modifier = Modifier.width(160.dp)
            )
            // Transparent overlay to intercept taps
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(onClick = onSearchClick)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(R.drawable.ic_account),
            contentDescription = "Profile",
            tint = Color.Black,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun TopBarPreview() {
    SanguosuoClientTheme() {
        SanguosuoTopBar(modifier = Modifier.fillMaxWidth())
    }
}