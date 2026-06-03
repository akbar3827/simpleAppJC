package com.learn.tutorialcompose

val CodeFirstScreen = """
package com.learn.tutorialcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen
import com.learn.tutorialcompose.ui.theme.BgGray
import com.learn.tutorialcompose.ui.theme.BgTextField
import com.learn.tutorialcompose.ui.theme.BottomNavIconColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun NavGraphBuilder.firstNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.FirstScreen.route) {
        FirstScreen(
            navController = navController,
            vm = vm
        )
    }
}


@Composable
fun FirstScreen(
    navController: NavController,
    vm: MyViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 40.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "back to home screen",
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navController.popBackStack()
                }.padding(horizontal = 10.dp)
            )
        }
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (textField,
                inputButton) = createRefs()

            BasicTextField(
                value = vm.textFieldState2.value,
                singleLine = true,
                onValueChange = { vm.textFieldState2.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BgTextField)
                    .padding(
                        start = 12.dp,
                        end = 60.dp,
                        top = 14.dp,
                        bottom = 15.dp
                    )
                    .constrainAs(textField) {
                        top.linkTo(parent.top, margin = 50.dp)
                    },
                cursorBrush = SolidColor(Color.Black.copy(alpha = 0.3f)),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                decorationBox = { innerTextField ->
                    if (vm.textFieldState2.value.isEmpty()) {
                        Text(
                            text = "Name",
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
                    .width(60.dp).constrainAs(inputButton) {
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
                    navController.navigate(Screen.DetailScreen.withArgs(vm.textFieldState2.value))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "enter",
                    tint = Color.White,
                    modifier = Modifier.scale(-1f, 1f)
                )
            }
        }
    }
}
"""
val CodeSecondScreen = """
package com.learn.tutorialcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.R
import com.learn.tutorialcompose.Screen
import com.learn.tutorialcompose.quotes
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

fun NavGraphBuilder.secondNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.SecondScreen.route) {
        SecondScreen(
            onNavigateToMainScreen = {
                navController.popBackStack()
            },
            vm = vm
        )
    }
}

@Composable
fun SecondScreen(
    onNavigateToMainScreen: () -> Unit,
    vm: MyViewModel
) {
    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light)
    )
    val vmString = vm.textFieldState.value
    val arrayOfString = vmString.split("")

    val context = LocalContext.current
    val data = vm.responState


    val painter = painterResource(id = R.drawable.an_miaoyi)
    val description = "an miaoyi's my wife!!"
    val title = "who's husband of an miaoyi?"

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            sayHello,
            cardOfQuotes,
            cardOfImg,
            buttonToHome
        ) = createRefs()
        val guideline = createGuidelineFromTop(0.5f)

        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.5f)
                .constrainAs(sayHello) {
                    top.linkTo(cardOfImg.top)
                    bottom.linkTo(cardOfQuotes.top)
                    start.linkTo(parent.start)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontSize = 30.sp
                        )
                    ) {
                        append("H")
                    }
                    append("ello ")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Red,
                            fontSize = 30.sp
                        )
                    ) {
                        append(arrayOfString[1])
                    }

                    arrayOfString.map {
                        if (it != arrayOfString[1]) append(it)
                    }
                },
                color = Color(0xFF101010),
                fontSize = 20.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
            )
        }


        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .constrainAs(cardOfQuotes) {
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                quotes.forEach {
                    QuotesCard(
                        quote = it
                    )
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .constrainAs(cardOfImg) {
                    bottom.linkTo(guideline)
                    start.linkTo(cardOfQuotes.end)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                ImageCard(
                    painter = painter,
                    contentDescription = description,
                    title = title
                )
                LaunchedEffect(vm.responState) {
                    delay(3000L)
                    vm.loadData(context, "card.json  ")
                }
                data?.cards?.forEach {
                    ImageCard2(
                        painter = it?.imageUrl ?: "",
                        contentDescription = it?.description ?: "",
                        title = it?.title ?: ""
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .constrainAs(buttonToHome) {
                    top.linkTo(cardOfImg.bottom)
                    bottom.linkTo(cardOfQuotes.bottom)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    onNavigateToMainScreen()
                }
            ) {
                Text("Back")
            }
        }
    }
}

@Composable
fun QuotesCard(
    quote: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .height(150.dp)
            .background(Color.Transparent)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = quote,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 200f
                        )
                    )
                    .fillMaxSize()
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ImageCard2(
    painter: String,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            AsyncImage(
                model = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 200f
                        )
                    )
                    .fillMaxSize()
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}
"""
val CodeThirdScreen = """
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
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
import com.learn.tutorialcompose.ui.theme.BgGray
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
            dropDown,
            box,
            circularProgressBar
        ) = createRefs()

        Box(
            Modifier.constrainAs(dropDown) {
                top.linkTo(parent.top)
            }
                .padding(top = 45.dp, start = 15.dp, end = 15.dp)
        ) {
            DropDown(
                text = "dropDown",
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    "this is now revealed", Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Green)
                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .constrainAs(box) {
                    top.linkTo(dropDown.bottom)
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
        Box(
            Modifier
                .fillMaxWidth()
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
fun DropDown(
    text: String,
    modifier: Modifier = Modifier,
    initiallyOpened: Boolean = false,
    content: @Composable () -> Unit
) {
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }
    val alpha = animateFloatAsState(
        targetValue = if (isOpen) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        )
    )
    val rotateX = animateFloatAsState(
        targetValue = if (isOpen) 1f else -90f,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    Column(
        modifier.fillMaxWidth()
            .background(color = BgGray)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open or close the drop down",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .scale(1f, if(isOpen) -1f else 1f)
            )
        }
        Spacer(Modifier.height(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    rotationX = rotateX.value
                    transformOrigin = TransformOrigin(0.5f, 0f)
                }
                .alpha(alpha.value)
        ) {
            content()
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
        targetValue = if (animationPlayed) percentage else 0f,
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
}""".trimIndent()
val CodeFourthScreen = """
    package com.learn.tutorialcompose.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
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
import com.learn.tutorialcompose.ui.theme.BgGray
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun NavGraphBuilder.fourthNav(navController: NavController, vm: MyViewModel) {
    composable(Screen.FourthScreen.route) {
        FourthScreen(
            navController = navController,
            vm = vm
        )
    }
}

@Composable
fun FourthScreen(
    navController: NavController,
    vm: MyViewModel
) {

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(top = 40.dp)
    ) {
        val (
            buttonHomme,
            timer
        ) = createRefs()

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .constrainAs(buttonHomme) {
                top.linkTo(parent.top)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "back to home page",
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navController.popBackStack()
                }.padding(horizontal = 10.dp)
            )
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
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
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
            value = initialValue
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
            val beta = (125 + 290f * value) * (PI / 180f).toFloat()
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
            color = BgGray
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
            Text(
                if(isTimerRunning && currentTime > 0L) "Stop" else "Start",
                color = BgGray
                )
        }
    }
}
""".trimIndent()
val CodeFifthScreen = """
    package com.learn.tutorialcompose.screens

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.interaction.MutableInteractionSource
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.aspectRatio
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.lazy.grid.GridCells
    import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Add
    import androidx.compose.material.icons.filled.AlternateEmail
    import androidx.compose.material.icons.filled.ArrowBackIosNew
    import androidx.compose.material.icons.filled.KeyboardArrowDown
    import androidx.compose.material.icons.filled.Link
    import androidx.compose.material.icons.filled.PersonAddAlt
    import androidx.compose.material.icons.filled.PlayArrow
    import androidx.compose.material3.Icon
    import androidx.compose.material3.SecondaryTabRow
    import androidx.compose.material3.Tab
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.draw.rotate
    import androidx.compose.ui.draw.scale
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.painter.Painter
    import androidx.compose.ui.graphics.vector.ImageVector
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextOverflow
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.constraintlayout.compose.ConstraintLayout
    import androidx.navigation.NavController
    import androidx.navigation.NavGraphBuilder
    import androidx.navigation.compose.composable
    import com.learn.tutorialcompose.MyViewModel
    import com.learn.tutorialcompose.R
    import com.learn.tutorialcompose.Screen
    import com.learn.tutorialcompose.ui.theme.colorlink
    import com.learn.tutorialcompose.ui.theme.inactiveColor
    import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
    import com.learn.tutorialcompose.IconWithText
    import com.learn.tutorialcompose.ui.theme.BgGray

    fun NavGraphBuilder.fifthNav(navController: NavController, vm: MyViewModel) {
        composable(Screen.FifthScreen.route) {
            FifthScreen(
                onNavigateToMainScreen = {
                    navController.popBackStack()
                },
                vm = vm
            )
        }
    }

    @Composable
    fun FifthScreen(
        onNavigateToMainScreen: () -> Unit,
        vm: MyViewModel
    ) {
        var selectedTabIndex by remember {
            mutableStateOf(0)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BgGray)
                .padding(top = 50.dp)
        ) {
            TopBar(
                "4kbrr.io",
                modifier = Modifier,
                onNavigateToMainScreen
            )
            Spacer(Modifier.height(40.dp))
            ProfileSection(
                text = "4kbrr.io", highllights = listOf(
                    IconWithText(
                        icon = R.drawable.image_highlight_apaaja,
                        text = "apa aja"
                    ),
                    IconWithText(
                        icon = R.drawable.image_highlight_books,
                        text = "books"
                    )
                )
            )
            Spacer(Modifier.height(10.dp))
            PostTabView(
                imageWithText = listOf(
                    IconWithText(
                        icon = R.drawable.ic_grid,
                        text = "Posts"
                    ),
                    IconWithText(
                        icon = R.drawable.ic_reels,
                        text = "Reels"
                    ),
                    IconWithText(
                        icon = R.drawable.ic_repost,
                        text = "Repost"
                    ),
                    IconWithText(
                        icon = R.drawable.ic_profile,
                        text = "Tagged"
                    )
                )
            ) {
                selectedTabIndex = it
            }
            when(selectedTabIndex) {
                0 -> PostsSection(
                    listOf(
                        R.drawable.image_highlight_books,
                        R.drawable.coffe,
                        R.drawable.software_dev,
                        R.drawable.sunday,
                        R.drawable.keepgoing,
                        R.drawable.github_readme,
                        R.drawable.bestcombocolor,
                        R.drawable.fallwallpaper,
                        R.drawable.aestheticdesign,
                        R.drawable.piginainicial,
                        R.drawable.london,
                        R.drawable.osaka
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }


    @Composable
    fun TopBar(
        name: String,
        modifier: Modifier = Modifier,
        navToBackScreen: () -> Unit
    ) {
        ConstraintLayout(modifier.fillMaxWidth()) {
            val (
                IconAdd,
                Name,
                IconArrowDown,
                IconForum,
                IconMenu
            ) = createRefs()

            Icon(
                Icons.Default.ArrowBackIosNew,
                "Back to home page",
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
                    .constrainAs(IconAdd) {
                        start.linkTo(parent.start, margin = 12.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        navToBackScreen()
                    }
            )
            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.constrainAs(Name) {
                    start.linkTo(IconAdd.end, margin = 20.dp)
                    end.linkTo(IconForum.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "ArrowDropDown",
                tint = Color.White,
                modifier = Modifier
                    .size(18.dp)
                    .constrainAs(IconArrowDown) {
                        start.linkTo(Name.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.threads_icon),
                contentDescription = "Forum",
                tint = Color.White,
                modifier = Modifier
                    .size(45.dp)
                    .constrainAs(IconForum) {
                        end.linkTo(IconMenu.start, margin = 18.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_hamburger),
                contentDescription = "Menu",
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(IconMenu) {
                        end.linkTo(parent.end, margin = 20.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }


    @Composable
    fun ProfileSection(
        text: String,
        modifier: Modifier = Modifier,
        highllights: List<IconWithText>
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        ) {
            val (
                ImageProfile,
                Name,
                Post,
                Followers,
                Following,
                profileDescription,
                bunners,
                EditProfile,
                ShareProfile,
                DiscoverPeople,
                hightlight
            ) = createRefs()

            RoundImage(
                image = painterResource(id = R.drawable.profile_image),
                modifier = Modifier
                    .constrainAs(ImageProfile) {
                        start.linkTo(parent.start)
                    }
                    .size(90.dp)
            )
            Text(
                text = text,
                fontSize = 18.sp,
                modifier = modifier.constrainAs(Name) {
                    start.linkTo(ImageProfile.end, margin = 20.dp)
                    top.linkTo(parent.top)
                },
                color = Color.White
            )
            ProfileStat(text = "posts", number = "4537", modifier.constrainAs(Post) {
                top.linkTo(Name.bottom, margin = 8.dp)
                start.linkTo(ImageProfile.end, margin = 20.dp)
            })
            ProfileStat(text = "followers", number = "4.8M", modifier.constrainAs(Followers) {
                top.linkTo(Name.bottom, margin = 8.dp)
                start.linkTo(Post.end)
                end.linkTo(Following.start)
            })
            ProfileStat(text = "following", number = "41", modifier.constrainAs(Following) {
                top.linkTo(Name.bottom, margin = 8.dp)
                start.linkTo(Followers.end)
                end.linkTo(parent.end)
            })
            ProfileDescription(
                text = ""${'"'}
                        AS AN ANDROID DEVELOPER
                        have been 1 years more learned frontend android
                        development.
                        I also like reading book such economic and self
                        improvement.
                        ""${'"'}.trimIndent(),
                link = "medium.com/@akbarkurniawan3827",
                modifier = modifier.constrainAs(profileDescription) {
                    top.linkTo(ImageProfile.bottom, margin = 5.dp)
                },
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bunners) {
                        top.linkTo(profileDescription.bottom)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Banners(
                    text = "4kbrr.io",
                    color = Color.White,
                    icon = Icons.Default.AlternateEmail
                )
                Banners(
                    text = "low battery aetunu, ruino",
                    color = Color.White,
                    icon = Icons.Default.PlayArrow
                )
                Banners()
            }
            BoxProfile(
                text = "Edit profile", modifier = Modifier
                    .constrainAs(EditProfile)
                    {
                        top.linkTo(bunners.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(ShareProfile.start)
                    }
                    .clip(RoundedCornerShape(8.dp)))
            BoxProfile(
                text = "Share profile", modifier = Modifier
                    .constrainAs(ShareProfile)
                    {
                        top.linkTo(bunners.bottom)
                        start.linkTo(EditProfile.end)
                        end.linkTo(DiscoverPeople.start)
                    }
                    .clip(RoundedCornerShape(8.dp)))
            BoxProfile(
                modifier = Modifier
                    .constrainAs(DiscoverPeople)
                    {
                        top.linkTo(bunners.bottom)
                        start.linkTo(ShareProfile.end)
                        end.linkTo(parent.end)
                    }
                    .clip(RoundedCornerShape(8.dp)))
            LazyRow(
                modifier = modifier.constrainAs(hightlight) {
                    top.linkTo(DiscoverPeople.bottom, margin = 20.dp)
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AddHighlight(modifier = Modifier.size(70.dp))
                        Spacer(Modifier.height(2.dp))
                        Text("New", color = Color.White, fontSize = 12.sp)
                    }
                }
                items(highllights.size) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RoundImage(
                            painterResource(id = highllights[it].icon),
                            modifier = Modifier.size(70.dp)
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(highllights[it].text, color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }
    }


    @Composable
    fun ProfileDescription(
        text: String,
        link: String,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                color = Color.White,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Link, contentDescription = "link",
                    modifier = Modifier
                        .rotate(-55f)
                        .size(20.dp),
                    tint = colorlink
                )
                Text(
                    text = link,
                    color = colorlink,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }


    @Composable
    fun PostTabView(
        modifier: Modifier = Modifier,
        imageWithText: List<IconWithText>,
        onTabSelected: (selectedIndex: Int) -> Unit
    ) {
        var selectedTabIndex by remember {
            mutableStateOf(0)
        }

        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = Color.White,
            modifier = modifier,
            divider = {},
            indicator = {
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(selectedTabIndex)
                        .padding(horizontal = 10.dp),
                    color = Color.White,
                    height = 1.dp
                )
            }
        ) {
            imageWithText.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        onTabSelected(index)
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = inactiveColor
                ) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.text,
                        tint = if(selectedTabIndex == index) Color.White else inactiveColor,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                    )
                }
            }
        }
    }


    @Composable fun PostsSection(
        posts: List<Int>,
        modifier: Modifier = Modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.scale(1.01f).padding(top = 2.dp)
        ) {
            items(posts.size) {
                Image(
                    painter = painterResource(id = posts[it]),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(3f / 4f)
                        .height(200.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black
                        ),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }


    @Composable
    fun RoundImage(
        image: Painter,
        modifier: Modifier = Modifier,
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = modifier
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .border(
                    width = 3.dp,
                    color = Color.Gray.copy(alpha = 0.4f),
                    shape = CircleShape
                )
                .padding(7.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun AddHighlight(
        modifier: Modifier
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = modifier
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .border(
                    width = 0.7.dp,
                    color = Color.White,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .padding(22.dp),
            tint = Color.White
        )
    }

    @Composable
    fun ProfileStat(
        text: String,
        number: String,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier
        ) {
            Text(
                text = number,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }

    @Composable
    fun Banners(
        modifier: Modifier = Modifier,
        color: Color = Color.Gray.copy(alpha = 0.6f),
        text: String = "Add",
        icon: ImageVector = Icons.Default.Add
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(horizontal = 4.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(14.dp))
                .border(
                    width = 0.3.dp,
                    color = Color.Gray.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(horizontal = 6.dp, vertical = 4.dp)
        ) {
            Icon(
                icon, contentDescription = null, tint = color, modifier = modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = text,
                fontSize = 12.sp,
                color = color,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }

    @Composable
    fun BoxProfile(
        text: String = "",
        modifier: Modifier = Modifier
    ) {
        Box(
            modifier = modifier
                .background(Color.Gray.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            if (text.isNotBlank()) {
                Text(
                    text = text,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = modifier.padding(horizontal = 45.dp, vertical = 8.dp)
                )
            } else {
                Icon(
                    Icons.Default.PersonAddAlt,
                    contentDescription = "show other account",
                    tint = Color.White,
                    modifier = modifier
                        .padding(6.dp)
                        .size(20.dp)
                )
            }
        }
    }
""".trimIndent()
val CodeSixthScreen = """
    package com.learn.tutorialcompose.screens

    import android.Manifest
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.interaction.MutableInteractionSource
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowBackIosNew
    import androidx.compose.material.icons.filled.Check
    import androidx.compose.material3.Button
    import androidx.compose.material3.Icon
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.DisposableEffect
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.unit.dp
    import androidx.lifecycle.Lifecycle
    import androidx.lifecycle.LifecycleEventObserver
    import androidx.lifecycle.compose.LocalLifecycleOwner
    import androidx.navigation.NavController
    import androidx.navigation.NavGraphBuilder
    import androidx.navigation.compose.composable
    import com.google.accompanist.permissions.ExperimentalPermissionsApi
    import com.google.accompanist.permissions.isGranted
    import com.google.accompanist.permissions.rememberMultiplePermissionsState
    import com.google.accompanist.permissions.shouldShowRationale
    import com.learn.tutorialcompose.ListItem
    import com.learn.tutorialcompose.MyViewModel
    import com.learn.tutorialcompose.Screen

    fun NavGraphBuilder.sixthNav(navController: NavController, vm: MyViewModel) {
        composable(Screen.SixthScreen.route) {
            SixthScreen(
                onNavigateToMainScreen = {
                    navController.popBackStack()
                },
                vm = vm
            )
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun SixthScreen(
        onNavigateToMainScreen: () -> Unit,
        vm: MyViewModel
    ) {
        var items by remember {
            mutableStateOf(
                (1..20).map {
                    ListItem(
                        name = "item ${'$'}it",
                        isSelected = false
                    )
                }
            )
        }
        val permissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
            )
        )
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(key1 = lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    permissionState.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer = observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }


        Column(modifier = Modifier.padding(top = 40.dp)) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "back to home screen",
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onNavigateToMainScreen()
                    }.padding(horizontal = 10.dp)
                )
            }
            LazyColumn(Modifier
                .height(400.dp)
                .fillMaxWidth()) {
                items(items.size) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                items = items.mapIndexed { i, item ->
                                    if (i == it) {
                                        item.copy(isSelected = !item.isSelected)
                                    } else {
                                        item
                                    }
                                }
                            }
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = items[it].name)
                        if (items[it].isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "selected",
                                tint = Color.Green,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                permissionState.permissions.forEach { perm ->
                    when (perm.permission) {
                        Manifest.permission.CAMERA -> {
                            when {
                                perm.status.isGranted -> {
                                    Text(text = "Camera permissioon is accepted")
                                }

                                !perm.status.isGranted -> {
                                    Text(text = "Camera permissioon isn't granted")
                                }

                                perm.status.shouldShowRationale -> {
                                    Text(
                                        text = ""${'"'}Camera permission was permanently denied.
                                        You can enable it in the app settings.
                                    ""${'"'}.trimMargin()
                                    )
                                }
                            }
                        }

                        Manifest.permission.RECORD_AUDIO -> {
                            when {
                                perm.status.isGranted -> {
                                    Text(text = "Record audio permissioon is accepted")
                                }

                                !perm.status.isGranted -> {
                                    Text(text = "Record Audio permissioon isn't granted")
                                }

                                perm.status.shouldShowRationale -> {
                                    Text(
                                        text = ""${'"'}Recond audio permission was permanently denied.
                                        You can enable it in the app settings.
                                    ""${'"'}.trimMargin()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
""".trimIndent()
