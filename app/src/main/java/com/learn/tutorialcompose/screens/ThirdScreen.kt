package com.learn.tutorialcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen
import kotlin.random.Random

fun NavGraphBuilder.thirdNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.ThirdScreen.route) {
        ThirdScreen(
            onNavigateToMainScreen = {
                navController.popBackStack()
            },
            vm = vm
        )
    }
}

@Composable
fun ThirdScreen(
    onNavigateToMainScreen: () -> Unit,
    vm: MyViewModel
) {

    val colorr = remember { mutableStateOf(Color.Red) }
    println(colorr)

    Column(
        modifier = Modifier
            .padding(top = 50.dp)
    ) {
        Button(
            onClick = {
                onNavigateToMainScreen()
            }
        ) {
            Text("Back to main screen")
        }
        Column(
            Modifier.fillMaxWidth()
                .weight(1f)
        ) {
            RandomColor(
                Modifier.weight(1f),
                {
                    colorr.value = it
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(100.dp)
                    .background(colorr.value)
            )
        }
    }
}


@Composable
fun RandomColor(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
            .clickable {
                changeColor(updateColor)
            }
    )
}
fun changeColor(updateColor: (Color) -> Unit) {
    updateColor(
        Color(
            Random.nextFloat(),
            Random.nextFloat(),
            Random.nextFloat(),
            1f
        )
    )
}