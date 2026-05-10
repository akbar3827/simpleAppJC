package com.learn.tutorialcompose

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MyViewModel : ViewModel() {
    var textFieldState = mutableStateOf("")

    var responState by mutableStateOf<Response?>(null)

    fun loadData(context: Context, jsonFile: String) {
        val result = loadRespon(context)
        responState = result
    }
}


fun loadRespon(context: Context): Response? {
    return try {
        val jsonString = readJson(context)

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