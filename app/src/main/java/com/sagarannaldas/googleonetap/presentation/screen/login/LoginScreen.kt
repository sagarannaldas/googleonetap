package com.sagarannaldas.googleonetap.presentation.screen.login

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sagarannaldas.googleonetap.presentation.screen.common.StartActivityForResult
import com.sagarannaldas.googleonetap.presentation.screen.common.signIn

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val signedInState by loginViewModel.signedInState
    val messageBarState by loginViewModel.messageBarState
    val activity = LocalContext.current as Activity

    Scaffold(
        topBar = { LoginTopBar() },
        content = {
            LoginContent(
                signedInState = signedInState,
                messageBarState = messageBarState,
                onButtonClick = {
                    loginViewModel.saveSignedInState(signedIn = true)
                }
            )
        }
    )

    StartActivityForResult(
        key = signedInState,
        onResultReceived = { tokenId ->
            Log.d("LoginScreen", "Token ID: $tokenId")
        },
        onDialogDismissed = {
            loginViewModel.saveSignedInState(signedIn = false)
        }
    ) { activityLauncher ->
        if (signedInState) {
            signIn(
                activity = activity,
                launchActivityResult = { intentSenderRequest ->
                    activityLauncher.launch(intentSenderRequest)
                },
                accountNotFound = {
                    loginViewModel.saveSignedInState(signedIn = false)
                    loginViewModel.updateMessageBarState()
                }
            )
        }
    }
}