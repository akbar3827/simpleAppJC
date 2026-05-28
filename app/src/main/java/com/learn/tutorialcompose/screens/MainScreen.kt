package com.learn.tutorialcompose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.screens.fifthNav
import com.learn.tutorialcompose.screens.firstScreen
import com.learn.tutorialcompose.screens.fourthNav
import com.learn.tutorialcompose.screens.secondNav
import com.learn.tutorialcompose.screens.sixthNav
import com.learn.tutorialcompose.screens.thirdNav

@Composable fun HomeScreen(
    navController: NavController,
    vm: MyViewModel
) {
//    firstScreen(navController, vm)
//    secondNav(navController, vm)
//    thirdNav(navController, vm)
//    fourthNav(navController, vm)
//    fifthNav(navController, vm)
//    sixthNav(navController, vm)
//    ProfileScreen(navController, vm)
//    BunchOfCodesScreen(navController, vm)

    Text(text = "Main Screen")
}