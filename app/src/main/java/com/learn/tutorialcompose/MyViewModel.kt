package com.learn.tutorialcompose

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.ramcosta.composedestinations.generated.destinations.BunchOfCodesScreenDestination
import com.ramcosta.composedestinations.generated.destinations.FifthScreenDestination
import com.ramcosta.composedestinations.generated.destinations.FirstScreenDestination
import com.ramcosta.composedestinations.generated.destinations.FourthScreenDestination
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SecondScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SixthScreenDestination
import com.ramcosta.composedestinations.generated.destinations.ThirdScreenDestination

class MyViewModel : ViewModel() {
    var textFieldState:MutableState<String> = mutableStateOf("")
    var textFieldState2:MutableState<String> = mutableStateOf("")
    var textFieldState3:MutableState<String> = mutableStateOf("")
    var responState: Response? by mutableStateOf(null)

    val codeMap:Map<String,String> = mapOf(
        "MainActivity" to CodeOfMainActivity,
        "MyApp" to CodeofMyApp,
        "ViewModel" to CodeOfMyViewModel,
        HomeScreenDestination.route to CodeOfMainScreen,
        BunchOfCodesScreenDestination.route to CodeOfBunchOfCodesScreen,
        ProfileScreenDestination.route to CodeOfProfileScreen,
        FirstScreenDestination.route to CodeOfFirstScreen,
        SecondScreenDestination.route to CodeofSecondScreen,
        ThirdScreenDestination.route to CodeOfThirdScreen,
        FourthScreenDestination.route to CodeOfFourthScreen,
        FifthScreenDestination.route to CodeofFifthScreen,
        SixthScreenDestination.route to CodeOfSixthScreen,
        "CodesScreen" to CodeOfCodesScreen,
        "CodeOfDetailScreen" to CodeOfDetailScreen,
        "BottomNavBar" to CodeOfBottomNavbar,
        "CustomTypeData" to CodeOfCustomTypeData,
        "Respons" to CodeOfResponse,
        "Screen" to CodeOfScreen
    )
    val CodeList = listOf(
        StringScreen(name = "MainActivity", key = "MainActivity"),
        StringScreen(name = "MyApp", key = "MyApp"),
        StringScreen(name = "ViewModel", key = "ViewModel"),
        StringScreen(name = "HomeScreen", key = HomeScreenDestination.route),
        StringScreen(name = "BunchOfCodes", key = BunchOfCodesScreenDestination.route),
        StringScreen(name = "ProfileScreen", key = ProfileScreenDestination.route),
        StringScreen(name = "FirstScreen", key = FirstScreenDestination.route),
        StringScreen(name = "SecondScreen", key = SecondScreenDestination.route),
        StringScreen(name = "ThirdScreen", key = ThirdScreenDestination.route),
        StringScreen(name = "FourthScreen", key = FourthScreenDestination.route),
        StringScreen(name = "FifthScreen", key = FifthScreenDestination.route),
        StringScreen(name = "SixthScreen", key = SixthScreenDestination.route),
        StringScreen(name = "CodesScreen", key = "CodesScreen"),
        StringScreen(name = "DetailScreen", key = "CodeOfDetailScreen"),
        StringScreen(name = "BottomNavBar", key = "BottomNavBar"),
        StringScreen(name = "CustomTypeData", key = "CustomTypeData"),
        StringScreen(name = "Respons", key = "Respons"),
        StringScreen(name = "Screen", key = "Screen")
    )
    val navScreenIsVisible:List<String> = listOf(
        HomeScreenDestination.route,
        BunchOfCodesScreenDestination.route,
        ProfileScreenDestination.route
    )

    fun loadData(context: Context, jsonFile: String) {
        val result = loadRespon(context)
        responState = result
    }
}


fun loadRespon(context: Context): Response? {
    return try {
        val jsonString: String = readJson(context)

        Gson().fromJson(jsonString, Response::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun readJson(context: Context): String {
    return context.assets.open("card.json")
        .bufferedReader()
        .use { it.readText() }
}