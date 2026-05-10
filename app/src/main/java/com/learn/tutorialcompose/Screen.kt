package com.learn.tutorialcompose

sealed class Screen(val route: String) {
    object MainScreen: Screen("home")
    object SecondScreen: Screen("secondscreen")
    object ThirdScreen: Screen("thirdscreen")
    object FourthScreen: Screen("fourthscreen")
    object FifthScreen: Screen("fifthscreen")
    object SixthScreen: Screen("sixthscreen")
}