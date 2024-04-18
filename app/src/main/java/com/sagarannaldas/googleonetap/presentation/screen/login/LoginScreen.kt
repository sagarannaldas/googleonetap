package com.sagarannaldas.googleonetap.presentation.screen.login

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sagarannaldas.googleonetap.domain.model.ApiRequest
import com.sagarannaldas.googleonetap.domain.model.ApiResponse
import com.sagarannaldas.googleonetap.navigation.Screen
import com.sagarannaldas.googleonetap.presentation.screen.common.StartActivityForResult
import com.sagarannaldas.googleonetap.presentation.screen.common.signIn
import com.sagarannaldas.googleonetap.util.RequestState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val signedInState by loginViewModel.signedInState
    val messageBarState by loginViewModel.messageBarState
    val apiResponse by loginViewModel.apiResponse
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
            loginViewModel.verifyTokenOnBackend(
                request = ApiRequest(tokenId = tokenId)
            )
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

    LaunchedEffect(key1 = apiResponse) {
        when (apiResponse) {
            is RequestState.Success -> {
                val response = (apiResponse as RequestState.Success<ApiResponse>).data.success
                if (response) {
                    navigateToProfileScreen(navController)
                } else {
                    loginViewModel.saveSignedInState(signedIn = false)
                }
            }
            else -> {}
        }
    }
}

private fun navigateToProfileScreen(
    navController: NavHostController
) {
    navController.navigate(Screen.Profile.route) {
        popUpTo(Screen.Login.route) {
            inclusive = true
        }
    }
}