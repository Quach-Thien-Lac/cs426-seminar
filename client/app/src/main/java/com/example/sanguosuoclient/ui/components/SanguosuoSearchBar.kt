package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme
import com.example.sanguosuoclient.ui.theme.primaryLight


@Composable
fun SanguosuoSearchBar (
    query: String,
    onQueryChange: (String) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(primaryLight)
                .padding(horizontal = 10.dp)
                .weight(0.3f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }

        // 3. The Vertical Divider
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(Color.Black)
        )

        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = MaterialTheme.typography.displaySmall,
            enabled = enabled,
            placeholder = {
                Text(text = "Search...", color = Color.Gray)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SanguosuoClientTheme() {
        SanguosuoSearchBar(
            query = "",
            onQueryChange = {},
            enabled = true,
        )
    }
}