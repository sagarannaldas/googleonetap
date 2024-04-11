package com.sagarannaldas.googleonetap.presentation.screen.login

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sagarannaldas.googleonetap.domain.model.MessageBarState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(
        topBar = { LoginTopBar() },
        content = {
            LoginContent(
                signedInState = true,
                messageBarState = MessageBarState(),
                onButtonClick = {}
            )
        }
    )
}