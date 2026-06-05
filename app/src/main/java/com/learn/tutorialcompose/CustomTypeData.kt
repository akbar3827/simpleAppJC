package com.learn.tutorialcompose

import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

data class IconWithText(
    val icon: Int,
    val text: String
)

data class BottomNavItem(
    val label: String,
    val icon: Int,
    val destination: DirectionDestinationSpec
)

data class SwitchScreen(
    val name: String,
    val screen: () -> Unit
)
data class StringScreen(
    val name: String,
    val key: String
)

data class ListItem(
    val name: String,
    val isSelected: Boolean
)
data class SwitchScreenCode(
    val name: String,
    val screen: () -> Unit,
    val code: String
)