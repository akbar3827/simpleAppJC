package com.learn.tutorialcompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen

fun NavGraphBuilder.detailScreen(navController: NavController, name: String) {
    composable(
        route = Screen.DetailScreen.route + "/{name}",
        arguments = listOf(
            navArgument(name = "name", builder = {
                type = NavType.StringType
            })
        )
    ) { entry ->
        DetailScreen(
            navController = navController,
            name = entry.arguments?.getString("name") ?: ""
        )
    }
}

@Composable
fun DetailScreen(
    navController: NavController,
    name: String?,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp)) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "back to home screen",
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        navController.popBackStack()
                    }
                    .padding(horizontal = 10.dp)
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Hello, $name")
        }
    }
}