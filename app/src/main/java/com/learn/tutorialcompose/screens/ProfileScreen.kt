package com.learn.tutorialcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.R
import com.learn.tutorialcompose.Screen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination<RootGraph>
@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    vm: MyViewModel
) {
    Column(Modifier.fillMaxSize()) {
        var progress: Float by remember {
            mutableStateOf(0f)
        }
        val contex = LocalContext.current
        val motionScene = remember {
            contex.resources
                .openRawResource(R.raw.motion_scene)
                .readBytes()
                .decodeToString()
        }
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .layoutId("box")
            )
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Green,
                        shape = CircleShape
                    )
                    .layoutId("profile_pic")
            )
            Text(
                text = "Akbarrrrr",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.layoutId("username")
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Slider(
            value = progress,
            onValueChange = {
                progress = it
            },
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileHeader(progress: Float) {

}