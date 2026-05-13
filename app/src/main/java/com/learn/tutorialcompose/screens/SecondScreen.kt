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

data class Item(
    val id: String,
    val title: String,
    val img: String,
    val description: String
)

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

    val constrains = ConstraintSet {
        val sayHello = createRefFor("sayhello")
        val cardOfQuotes = createRefFor("cardofquotes")
        val cardOfImg = createRefFor("cardofimg")
        val buttonToHome = createRefFor("buttontohome")
        val guideline = createGuidelineFromTop(0.5f)

        constrain(sayHello) {
            top.linkTo(cardOfImg.top)
            bottom.linkTo(cardOfQuotes.top)
            start.linkTo(parent.start)
        }
        constrain(cardOfQuotes) {
            top.linkTo(guideline)
//            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
        }
        constrain(cardOfImg) {
//            top.linkTo(sayHello.bottom)
            bottom.linkTo(guideline)
            start.linkTo(cardOfQuotes.end)
        }
        constrain(buttonToHome) {
            top.linkTo(cardOfImg.bottom)
            bottom.linkTo(cardOfQuotes.bottom)
            end.linkTo(parent.end)
        }
    }

    ConstraintLayout(constrains, modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.5f)
                .layoutId("sayhello"),
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
                .layoutId("cardofquotes"),
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
                .layoutId("cardofimg"),
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
                .layoutId("buttontohome"),
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