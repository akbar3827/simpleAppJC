package com.learn.tutorialcompose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.learn.tutorialcompose.screens.fifthNav
import com.learn.tutorialcompose.screens.fourthNav
import com.learn.tutorialcompose.screens.mainNav
import com.learn.tutorialcompose.screens.secondNav
import com.learn.tutorialcompose.screens.sixthNav
import com.learn.tutorialcompose.screens.thirdNav

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val vm = viewModel<MyViewModel>()

    NavHost(navController, Screen.MainScreen.route) {
        mainNav(navController, vm)
        secondNav(navController, vm)
        thirdNav(navController, vm)
        fourthNav(navController, vm)
        fifthNav(navController, vm)
        sixthNav(navController, vm)
    }
}