package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme

@Composable
fun SanguosuoTitle(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.chinese_app_name),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun SanguosuoSmallTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.titleLarge,
        fontSize = 43.sp,
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun SanguosuoTitlePreview() {
//    SanguosuoClientTheme() {
//        SanguosuoSmallTitle(Modifier.wrapContentSize())
//    }
//}