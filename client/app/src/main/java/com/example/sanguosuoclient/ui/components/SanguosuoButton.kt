package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.theme.onBackgroundDark
import com.example.sanguosuoclient.ui.theme.onPrimaryLight
import com.example.sanguosuoclient.ui.theme.primaryLight
import com.example.sanguosuoclient.ui.theme.primaryRoundedRectangleShape

enum class SanguosuoButtonVariant {
    Normal, Outlined
}

@Composable
fun SanguosuoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: SanguosuoButtonVariant = SanguosuoButtonVariant.Normal,
    color: Color = primaryLight,
    enabled: Boolean = true,
    fullWidth: Boolean = true
) {
    val buttonModifier = if (fullWidth) modifier.fillMaxWidth() else Modifier

    when (variant) {
        SanguosuoButtonVariant.Normal -> {
            Button(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                    contentColor = Color.Black,
                ),
                border = BorderStroke(
                    width = with(LocalDensity.current) {5.toDp()},
                    color = Color.Black
                ),
                shape = primaryRoundedRectangleShape
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
        SanguosuoButtonVariant.Outlined-> {
            OutlinedButton(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                border = BorderStroke(
                    width = with(LocalDensity.current) {5.toDp()},
                    color = Color.Black
                )
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SanguosuoButtonPreview() {
//    Column(
//        modifier = Modifier.width(333.dp).height(333.dp)
//    ) {
//        SanguosuoButton(
//            textId = R.string.sign_in,
//            onClick = {},
//            color = primaryLight,
//             variant = SanguosuoButtonVariant.Outlined,
//            modifier = Modifier.wrapContentHeight()
//        )
//    }
//}