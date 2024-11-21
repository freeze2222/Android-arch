package com.compose.presentation.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.data.utils.Constants
import com.compose.presentation.screens.catsScreen.CatsScreen

@Composable
fun NavGraph(navController: NavHostController, contentPadding: PaddingValues) {
    NavHost(
        navController = navController, startDestination = Constants.NavDestinations.CATS_SCREEN
    ) {
        composable(Constants.NavDestinations.CATS_SCREEN) {
            EnterAnimation {
                CatsScreen(navController = navController, contentPadding = contentPadding)
            }
        }
    }
}