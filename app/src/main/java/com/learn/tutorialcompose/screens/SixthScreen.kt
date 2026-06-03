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
                    name = "item $it",
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
                                    text = """Camera permission was permanently denied.
                                    You can enable it in the app settings.
                                """.trimMargin()
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
                                    text = """Recond audio permission was permanently denied.
                                    You can enable it in the app settings.
                                """.trimMargin()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}