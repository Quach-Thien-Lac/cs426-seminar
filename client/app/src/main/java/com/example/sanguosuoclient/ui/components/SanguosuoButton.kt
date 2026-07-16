package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class SanguosuoButtonVariant {
    Primary, Secondary, Text
}

@Composable
fun SanguosuoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: SanguosuoButtonVariant = SanguosuoButtonVariant.Primary,
    enabled: Boolean = true,
    fullWidth: Boolean = true
) {
    val buttonModifier = if (fullWidth) modifier.fillMaxWidth().height(52.dp) else modifier.height(52.dp)

    when (variant) {
        SanguosuoButtonVariant.Primary -> {
            Button(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = text, style = MaterialTheme.typography.labelLarge)
            }
        }
        SanguosuoButtonVariant.Secondary -> {
            OutlinedButton(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = text, style = MaterialTheme.typography.labelLarge)
            }
        }
        SanguosuoButtonVariant.Text -> {
            androidx.compose.material3.TextButton(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled
            ) {
                Text(text = text, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
