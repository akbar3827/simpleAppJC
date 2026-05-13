package com.learn.tutorialcompose.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun NavGraphBuilder.mainNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.MainScreen.route) {
        MainScreen(
            onNavigateToSecondScreen = {
                navController.navigate(Screen.SecondScreen.route)
            },
            onNavigateToThirdScreen = {
                navController.navigate(Screen.ThirdScreen.route)
            },
            onNavigateToFourthScreen = {
                navController.navigate(Screen.FourthScreen.route)
            },
            onNavigateToFifthScreen = {
                navController.navigate(Screen.FifthScreen.route)
            },
            onNavigateToSixthScreen = {
                navController.navigate(Screen.SixthScreen.route)
            },
            vm = vm
        )
    }
}


@Composable
fun MainScreen(
    onNavigateToSecondScreen: () -> Unit,
    onNavigateToThirdScreen: () -> Unit,
    onNavigateToFourthScreen: () -> Unit,
    onNavigateToFifthScreen: () -> Unit,
    onNavigateToSixthScreen: () -> Unit,
    vm: MyViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier,
            snackbarHost = {
                Box(modifier = Modifier.fillMaxSize()) {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(horizontal = 16.dp, vertical = 30.dp)
                    )
                }
            }
        ) { padding ->
            val scope: CoroutineScope = rememberCoroutineScope()

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                BasicTextField(
                    value = vm.textFieldState.value,
                    singleLine = true,
                    onValueChange = { vm.textFieldState.value = it },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 8.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0x1D1D1B),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Gray)
                        .padding(vertical = 15.dp, horizontal = 10.dp),
                    cursorBrush = SolidColor(Color.Blue.copy(alpha = 0.3f)),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White
                    ),
                    decorationBox = { innerTextField ->
                        if (vm.textFieldState.value.isEmpty()) {
                            Text(
                                text = "Search",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                )
                Button(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Black.copy(alpha = 0.8f),
                        disabledContentColor = Color.White.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(30.dp),
                    onClick = {
                        scope.launch {
                            if (vm.textFieldState.value.lowercase().contains("second")) {
                                snackbarHostState.showSnackbar("5s would be move to ${vm.textFieldState.value}")
                                onNavigateToSecondScreen()
                            } else if (vm.textFieldState.value.lowercase().contains("third")) {
                                snackbarHostState.showSnackbar("5s would be move to ${vm.textFieldState.value}")
                                onNavigateToThirdScreen()
                            } else if (vm.textFieldState.value.lowercase().contains("fourth")) {
                                snackbarHostState.showSnackbar("5s would be move to ${vm.textFieldState.value}")
                                onNavigateToFourthScreen()
                            } else if (vm.textFieldState.value.lowercase().contains("fifth")) {
                                snackbarHostState.showSnackbar("5s would be move to ${vm.textFieldState.value}")
                                onNavigateToFifthScreen()
                            } else if (vm.textFieldState.value.lowercase().contains("sixth")) {
                                snackbarHostState.showSnackbar("5s would be move to ${vm.textFieldState.value}")
                                onNavigateToSixthScreen()
                            } else if (vm.textFieldState.value.isEmpty()) {
                                snackbarHostState.showSnackbar("the value's empty")
                            } else {
                                snackbarHostState.showSnackbar("Please enter the correct screen name")
                            }
                        }
                    }
                ) {
                    Text(
                        text = ">",
                        modifier = Modifier,
                        fontSize = 33.sp
                    )
                }
            }
        }
    }
}