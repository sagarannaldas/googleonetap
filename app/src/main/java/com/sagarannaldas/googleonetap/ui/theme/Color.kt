package com.sagarannaldas.googleonetap.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val ErrorRed = Color(0xFFFF6C60)
val InfoGreen = Color(0xFF00C096)
val LoadingBlue = Color(0xFF1A73E8)

val ColorScheme.topAppBarContentColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.White else Color.LightGray


val ColorScheme.topAppBarBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.LightGray else Color.Black
