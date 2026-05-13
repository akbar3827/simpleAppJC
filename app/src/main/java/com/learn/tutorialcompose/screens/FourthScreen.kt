package com.learn.tutorialcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen

fun NavGraphBuilder.fourthNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.FourthScreen.route) {
        FourthScreen(
            onNavigateToMainScreen = {
                navController.popBackStack()
            },
            vm = vm
        )
    }
}

@Composable
fun FourthScreen(
    onNavigateToMainScreen: () -> Unit,
    vm: MyViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("This is Screen-4")
        Button(
            onClick = {
                onNavigateToMainScreen()
            }
        ) {
            Text("Back to main screen")
        }
    }
}