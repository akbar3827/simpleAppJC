package com.learn.tutorialcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.learn.tutorialcompose.screens.BunchOfCodesScreen
import com.learn.tutorialcompose.screens.ProfileScreen
import com.learn.tutorialcompose.screens.fifthNav
import com.learn.tutorialcompose.screens.firstNav
import com.learn.tutorialcompose.screens.fourthNav
import com.learn.tutorialcompose.screens.homeScreen
import com.learn.tutorialcompose.screens.secondNav
import com.learn.tutorialcompose.screens.sixthNav
import com.learn.tutorialcompose.screens.thirdNav

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val vm = viewModel<MyViewModel>()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarRoutes = listOf(
        Screen.HomeScreen.route,
        Screen.BunchOfCodesScreen.route,
        Screen.ProfileScreen.route
    )
    val showBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Home",
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
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
        ) {
            homeScreen(navController, vm)
            BunchOfCodesScreen(navController, vm)
            ProfileScreen(navController, vm)
            firstNav(navController, vm)
            secondNav(navController, vm)
            thirdNav(navController, vm)
            fourthNav(navController, vm)
            fifthNav(navController, vm)
            sixthNav(navController, vm)
        }
    }
}

