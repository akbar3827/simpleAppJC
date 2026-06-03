package com.learn.tutorialcompose

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object SecondScreen: Screen("second_screen")
    object ThirdScreen: Screen("third_screen")
    object FourthScreen: Screen("fourth_screen")
    object FifthScreen: Screen("fifth_screen")
    object SixthScreen: Screen("sixth_screen")
    object FirstScreen: Screen("first_screen")
    object ProfileScreen: Screen("profile_screen")
    object BunchOfCodesScreen: Screen("bunch_of_codes_screen")
    object DetailScreen: Screen("detail_screen")
    object CodesScreen: Screen("codes_screen")
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}