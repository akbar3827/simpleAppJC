package com.learn.tutorialcompose.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen
import kotlin.random.Random

fun NavGraphBuilder.thirdNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.ThirdScreen.route) {
        ThirdScreen(
            onNavigateToMainScreen = {
                navController.popBackStack()
            },
            vm = vm
        )
    }
}

@Composable
fun ThirdScreen(
    onNavigateToMainScreen: () -> Unit,
    vm: MyViewModel
) {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(sizeState)
    val colorr = remember { mutableStateOf(Color.Red) }
    println(colorr)
    val colorr2 by rememberInfiniteTransition().animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        )
    )

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            button,
            buttonIncSize,
            box,
            circularProgressBar
        ) = createRefs()

        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .constrainAs(button) {
                    top.linkTo(box.bottom)
                },
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    onNavigateToMainScreen()
                }
            ) {
                Text("Back to main screen")
            }
            Button(
                onClick = {
                    sizeState += 20.dp
                }
            ) {
                Text(text = "increate the box size")
            }
        }
        Row(
            Modifier.fillMaxWidth()
                .height(200.dp)
                .fillMaxWidth()
                .constrainAs(box) {
                    top.linkTo(parent.top)
                },
            horizontalArrangement = Arrangement.Center
        ) {
            RandomColor(
                Modifier.size(200.dp),
                colorr2,
                {
                    colorr.value = it
                }
            )
            Box(
                modifier = Modifier
                    .size(size)
                    .background(colorr.value)
            )
        }
        Box(
            Modifier.fillMaxWidth()
                .height(400.dp)
                .constrainAs(circularProgressBar) {
                    top.linkTo(button.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            CircularProgressBar(
                percentage = 0.79866f,
                number = 53253,
                animDuration = 2500,
                animDelay = 600
            )
        }
    }
}


@Composable
fun RandomColor(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    updateColor: (Color) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable {
                changeColor(updateColor)
            }
    )
}
fun changeColor(updateColor: (Color) -> Unit) {
    updateColor(
        Color(
            Random.nextFloat(),
            Random.nextFloat(),
            Random.nextFloat(),
            1f
        )
    )
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.White.copy(alpha = 0.3f),
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 3f)
    ) {
        Canvas(Modifier.size(radius * 3f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (curPercentage.value * number).toString(),
            color = Color.White.copy(alpha = 0.8f),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}