package com.sagarannaldas.googleonetap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sagarannaldas.googleonetap.presentation.screen.login.LoginScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {

        }
    }
}