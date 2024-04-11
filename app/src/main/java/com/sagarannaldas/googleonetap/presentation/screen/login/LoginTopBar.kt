package com.sagarannaldas.googleonetap.presentation.screen.login

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sagarannaldas.googleonetap.ui.theme.topAppBarBackgroundColor
import com.sagarannaldas.googleonetap.ui.theme.topAppBarContentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopBar() {
    TopAppBar(
        modifier = Modifier.background(
            MaterialTheme.colorScheme.topAppBarBackgroundColor
        ),
        title = {
            Text(
                text = "Sign in",
                color = MaterialTheme.colorScheme.topAppBarContentColor
            )
        }
    )
}

@Composable
@Preview
fun LoginTopBarPreview() {
    LoginTopBar()
}