package com.learn.tutorialcompose

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learn.tutorialcompose.screens.BunchOfCodesScreen
import com.learn.tutorialcompose.screens.HomeScreen
import com.learn.tutorialcompose.screens.ProfileScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val vm = viewModel<MyViewModel>()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(name = "Home",
                        route = Screen.HomeScreen.route,
                        icon = R.drawable.ic_gemini
                    ),
                    BottomNavItem(
                        name = "Codes",
                        route = Screen.BunchOfCodesScreen.route,
                        icon = R.drawable.ic_codes
                    ),
                    BottomNavItem(
                        name = "Profile",
                        route = Screen.ProfileScreen.route,
                        icon = R.drawable.ic_profile
                    )
                ),
                navController = navController,
                onItemCLick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        NavHost(navController, Screen.HomeScreen.route) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(navController, vm)
            }
            composable(Screen.BunchOfCodesScreen.route) {
                BunchOfCodesScreen(navController, vm)
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(navController, vm)
            }
        }
    }
}