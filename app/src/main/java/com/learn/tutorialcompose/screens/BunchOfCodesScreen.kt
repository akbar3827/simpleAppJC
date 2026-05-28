package com.learn.tutorialcompose.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen

fun NavGraphBuilder.BunchOfCodesScreen(
    navController: NavController,
    vm: MyViewModel
) {
    composable(Screen.BunchOfCodesScreen.route) {
        BunchOfCodesScreen(onNavigationToBackScreen = {
            navController.popBackStack()
        }, vm = vm)
    }
}

@Composable fun BunchOfCodesScreen(
    onNavigationToBackScreen: () -> Unit,
    vm: MyViewModel
) {

}