package com.learn.tutorialcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen

fun NavGraphBuilder.sixthNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.SixthScreen.route) {
        SixthScreen(
            onNavigateToMainScreen = {
                navController.popBackStack()
            },
            vm = vm
        )
    }
}

@Composable
fun SixthScreen(
    onNavigateToMainScreen: () -> Unit,
    vm: MyViewModel
) {
    Column {
        Text("This is Screen-6")
        Button(
            onClick = {
                onNavigateToMainScreen()
            }
        ) {
            Text("Back to main screen")
        }
    }
}