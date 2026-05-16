package com.learn.tutorialcompose.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun NavGraphBuilder.fourthNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.FourthScreen.route) {
        FourthScreen(
            onNavigateToMainScreen = {
                navController.popBackStack()
            },
            vm = vm
        )
    }
}

@Composable
fun FourthScreen(
    onNavigateToMainScreen: () -> Unit,
    vm: MyViewModel
) {

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 18.dp)
    ) {
        val (
            buttonHomme,
            timer
        ) = createRefs()


        Button(
            onClick = {
                onNavigateToMainScreen()
            },
            modifier = Modifier.constrainAs(buttonHomme) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.padding(top = 20.dp)
        ) {
            Text("Back to main screen")
        }
        Box(modifier = Modifier.constrainAs(timer) {
            top.linkTo(buttonHomme.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }.padding(vertical = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Timer(
                10L * 1000L,
                Color.Green,
                Color.DarkGray,
                Color.Green,
                vm = vm,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun Timer(
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initalValue: Float = 1f,
    strokeWidth: Dp = 5.dp,
    vm: MyViewModel
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initalValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect( key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
        else if (currentTime == 0L) {
            isTimerRunning = false
            currentTime = totalTime
            value = initalValue
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = 125f,
                sweepAngle = 290f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            drawArc(
                color = activeBarColor,
                startAngle = 125f,
                sweepAngle = 290f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (290f * value + 125f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                points = listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 2f).toPx(),
                cap = StrokeCap.Round
            )
        }
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(0.9f)
        )
        Button(
            onClick = {
                    isTimerRunning = !isTimerRunning
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                if (!isTimerRunning || currentTime <= 0L) {
                    Color.Green
                } else {
                    Color.Red
                }
            )
        ) {
            Text(if(isTimerRunning && currentTime > 0L) "Stop" else "Start")
        }
    }
}