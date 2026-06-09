package com.learn.tutorialcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.SwitchScreen
import com.learn.tutorialcompose.ui.theme.BgTextField
import com.learn.tutorialcompose.ui.theme.BottomNavIconColor
import com.learn.tutorialcompose.ui.theme.ColorBox
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.FifthScreenDestination
import com.ramcosta.composedestinations.generated.destinations.FirstScreenDestination
import com.ramcosta.composedestinations.generated.destinations.FourthScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SecondScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SeventhScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SixthScreenDestination
import com.ramcosta.composedestinations.generated.destinations.ThirdScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Destination<RootGraph>(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    vm: MyViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.background(Color.DarkGray),
        snackbarHost = { SnackBarBox(snackbarHostState = snackbarHostState) }
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .background(color = Color.White)
                .padding()
        ) {
            val (
                textField,
                inputButton,
                bunchOfScreens
            ) = createRefs()

            BasicTextField(
                value = vm.textFieldState.value,
                singleLine = true,
                onValueChange = { vm.textFieldState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BgTextField)
                    .padding(start = 12.dp, end = 60.dp, top = 14.dp, bottom = 15.dp)
                    .constrainAs(textField) {
                        top.linkTo(parent.top, margin = 50.dp)
                    },
                cursorBrush = SolidColor(Color.Black.copy(alpha = 0.3f)),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                decorationBox = { innerTextField ->
                    if (vm.textFieldState.value.isEmpty()) {
                        Text(
                            text = "Search",
                            color = Color.Gray.copy(alpha = 0.7f),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
            Button(
                modifier = Modifier
                    .height(48.dp)
                    .width(60.dp)
                    .constrainAs(inputButton) {
                        top.linkTo(textField.top)
                        end.linkTo(textField.end, margin = 25.dp)
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BottomNavIconColor,
                    contentColor = Color.White,
                    disabledContainerColor = BottomNavIconColor,
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    scope.launch {
                        if (vm.textFieldState.value.isEmpty()) {
                            snackbarHostState.showSnackbar("the value's empty")
                        } else {
                            snackbarHostState.showSnackbar("Please enter the correct screen name")
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "enter",
                    tint = Color.White,
                    modifier = Modifier.scale(-1f, 1f)
                )
            }
            BunchOFScreen(
                modifier = Modifier.constrainAs(bunchOfScreens) {
                    top.linkTo(textField.bottom)
                },
                vm = vm,
                screens = listOf(
                    SwitchScreen(
                        name = "FirstScreen",
                        screen = { navigator.navigate(FirstScreenDestination) }
                    ),
                    SwitchScreen(
                        name = "SecondScreen",
                        screen = { navigator.navigate(SecondScreenDestination) }
                    ),
                    SwitchScreen(
                        name = "ThirdScreen",
                        screen = { navigator.navigate(ThirdScreenDestination) }
                    ),
                    SwitchScreen(
                        name = "FourthScreen",
                        screen = { navigator.navigate(FourthScreenDestination) }
                    ),
                    SwitchScreen(
                        name = "FifthScreen",
                        screen = { navigator.navigate(FifthScreenDestination) }
                    ),
                    SwitchScreen(
                        name = "SixthScreen",
                        screen = { navigator.navigate(SixthScreenDestination) }
                    ),
                    SwitchScreen(
                        name = "SeventhScreen",
                        screen = { navigator.navigate(SeventhScreenDestination) }
                    )
                )
            )
        }
    }
}

@Composable
fun BunchOFScreen(
    screens: List<SwitchScreen>,
    modifier: Modifier = Modifier,
    vm: MyViewModel
) {
    LazyVerticalGrid(
        modifier = modifier
            .background(color = Color.White)
            .padding(top = 30.dp)
            .padding(horizontal = 18.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val textInput = vm.textFieldState.value
        if (textInput.isNotEmpty()) {
            screens.forEach {
                if (it.name.lowercase().contains(textInput.lowercase().trim())) {
                    item {
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(ColorBox)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    it.screen()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it.name,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        } else {
            items(screens.size) {
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(ColorBox)
                        .clickable {
                            screens[it].screen()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = screens[it].name,
                        color = Color.White
                    )
                }
            }
        }
        item(span = { GridItemSpan(2) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(bottom = 200.dp)
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    buildAnnotatedString {
                        append("Created by")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append(" MOH. AKBAR KURNIAWAN")
                        }
                    },
                    color = Color.DarkGray
                )
            }
        }
    }
}


@Composable
fun SnackBarBox(
    snackbarHostState: SnackbarHostState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp, vertical = 30.dp)
        )
    }
}