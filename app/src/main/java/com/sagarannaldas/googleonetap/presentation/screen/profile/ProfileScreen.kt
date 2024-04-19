package com.sagarannaldas.googleonetap.presentation.screen.profile

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sagarannaldas.googleonetap.domain.model.ApiResponse
import com.sagarannaldas.googleonetap.domain.model.MessageBarState
import com.sagarannaldas.googleonetap.util.RequestState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            ProfileTopBar(
                onSave = {},
                onDeleteAllConfirmed = {}
            )
        },
        content = {
            ProfileContent(
                apiResponse = RequestState.Success(ApiResponse(success = true)),
                messageBarState = MessageBarState(),
                firstName = "",
                lastName = "",
                onFirsNameChanged = {},
                onLastNameChanged = {},
                emailAddress = "",
                profilePhoto = "",
                onSignOutClicked = {}
            )
        }
    )
}