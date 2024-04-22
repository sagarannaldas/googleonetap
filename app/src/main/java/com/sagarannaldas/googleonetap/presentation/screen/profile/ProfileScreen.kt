package com.sagarannaldas.googleonetap.presentation.screen.profile

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val user by profileViewModel.user
    val firstName by profileViewModel.firstName
    val lastName by profileViewModel.lastname
    val apiResponse by profileViewModel.apiResponse
    val messageBarState by profileViewModel.messageBarState

    Scaffold(
        topBar = {
            ProfileTopBar(
                onSave = {},
                onDeleteAllConfirmed = {}
            )
        },
        content = {
            ProfileContent(
                apiResponse = apiResponse,
                messageBarState = messageBarState,
                firstName = firstName,
                lastName = lastName,
                onFirsNameChanged = { profileViewModel.updateFirstName(it) },
                onLastNameChanged = { profileViewModel.updateLastName(it) },
                emailAddress = user?.emailAddress,
                profilePhoto = user?.profilePhoto,
                onSignOutClicked = {}
            )
        }
    )
}