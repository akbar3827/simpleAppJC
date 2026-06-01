package com.learn.tutorialcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.learn.tutorialcompose.ListItem
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.Screen
import com.learn.tutorialcompose.ui.theme.BgGray

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

@Composable
fun SixthScreen(
    onNavigateToMainScreen: () -> Unit,
    vm: MyViewModel
) {
    var Items by remember {
        mutableStateOf(
            (1..20).map {
                ListItem(
                    name = "item $it",
                    isSelected = false
                )
            }
        )
    }
    Column(modifier = Modifier.padding(top = 50.dp)) {
        Box(modifier = Modifier.fillMaxWidth().height(70.dp)) {
            Button(
                onClick = {
                    onNavigateToMainScreen()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "back to home screen"
                )
            }
        }
        LazyColumn(Modifier.fillMaxSize()) {
            items(Items.size) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Items = Items.mapIndexed { i, item ->
                                if(i == it) {
                                    item.copy(isSelected = !item.isSelected)
                                } else {
                                    item
                                }
                            }
                        }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = Items[it].name)
                    if(Items[it].isSelected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "selected",
                            tint = Color.Green,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}