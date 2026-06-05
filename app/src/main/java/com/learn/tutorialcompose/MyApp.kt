package com.learn.tutorialcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.BunchOfCodesScreenDestination
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val vm: MyViewModel = viewModel()
    // using currentBackStackEntryAsState() because i wanna change the UI too
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentDestination in vm.navScreenIsVisible) {
                BottomNavBar(
                    navController = navController,
                    items = listOf(
                        BottomNavItem(
                            label = "home",
                            icon = R.drawable.ic_gemini,
                            destination = HomeScreenDestination
                        ),
                        BottomNavItem(
                            label = "code",
                            icon = R.drawable.ic_gemini,
                            destination = BunchOfCodesScreenDestination
                        ),
                        BottomNavItem(
                            label = "profile",
                            icon = R.drawable.ic_gemini,
                            destination = ProfileScreenDestination
                        )
                    )
                ) { item ->
                    navController.navigate(item.destination.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    ) { padding ->
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = navController,
            modifier = Modifier.padding(padding),
            dependenciesContainerBuilder = {
                dependency(vm)
            }
        )
    }
}