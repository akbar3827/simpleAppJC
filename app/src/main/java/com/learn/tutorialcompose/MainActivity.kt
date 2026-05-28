package com.learn.tutorialcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learn.tutorialcompose.screens.FirstScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val isReady = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        // instplashScreen sebelum super.oncreate()
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