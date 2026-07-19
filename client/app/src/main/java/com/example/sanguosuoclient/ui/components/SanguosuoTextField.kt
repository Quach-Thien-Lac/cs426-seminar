package com.example.sanguosuoclient.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sanguosuoclient.R
import com.example.sanguosuoclient.ui.theme.SanguosuoClientTheme
import com.example.sanguosuoclient.ui.theme.primaryRoundedRectangleShape
import com.example.sanguosuoclient.ui.theme.surfaceContainerLowLight

@Composable
fun SanguosuoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    hintTextId: Int,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = stringResource(hintTextId),
                    style = MaterialTheme.typography.displaySmall,
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTrailingIconColor = Color.Black.copy(alpha = 0.5f),
                
                focusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                unfocusedPlaceholderColor = Color.LightGray,
                disabledPlaceholderColor = Color.Transparent,

                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedContainerColor = surfaceContainerLowLight,
                unfocusedContainerColor = surfaceContainerLowLight
            ),
            trailingIcon = trailingIcon,
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            shape = primaryRoundedRectangleShape
        )
    }
}

@Preview (showBackground = true)
@Composable
fun SanguosuoTextFieldPreview() {
    SanguosuoClientTheme(
    ) {
        Column(
            modifier = Modifier
                .width(333.dp)
                .height(333.dp)
        ) {
            SanguosuoTextField(
                value = "cc",
                onValueChange = {},
                label = "Email",
                hintTextId = R.string.email_place_holder,
                isError = false,
                trailingIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.visibility),
                            contentDescription = "",
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            )
        }
    }
}
