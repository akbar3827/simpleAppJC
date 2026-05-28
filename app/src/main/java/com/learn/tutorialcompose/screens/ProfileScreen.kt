package com.learn.tutorialcompose.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen

fun NavGraphBuilder.ProfileScreen(navController: NavController, vm: MyViewModel) {
    composable(Screen.ProfileScreen.route) {
        ProfileScreen(
            onNavigationToMainScreen = {
                navController.popBackStack()
            },
            vm = vm
        )
    }
}

@Composable
fun ProfileScreen(
    onNavigationToMainScreen: () -> Unit,
    vm: MyViewModel
) {

}