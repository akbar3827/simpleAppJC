package com.learn.tutorialcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val isReady = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        // installSplashScreen sebelum super.oncreate()
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        SplashScreen(splashScreen)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }

    fun SplashScreen(splashScreen: SplashScreen, ) {
        // show the splashScreen if the value of isReady is true
        splashScreen.setKeepOnScreenCondition { !isReady.value }

        lifecycleScope.launch {
            delay(3000)
            isReady.value = true
        }
    }
}