package com.learn.tutorialcompose

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object SecondScreen: Screen("secondscreen")
    object ThirdScreen: Screen("thirdscreen")
    object FourthScreen: Screen("fourthscreen")
    object FifthScreen: Screen("fifthscreen")
    object SixthScreen: Screen("sixthscreen")
    object FirstScreen: Screen("firstscreen")
    object ProfileScreen: Screen("profilescreen")
    object BunchOfCodesScreen: Screen("bunchofcodesscreen")
}