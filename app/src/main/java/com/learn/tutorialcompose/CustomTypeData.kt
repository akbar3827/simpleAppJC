package com.learn.tutorialcompose

import androidx.compose.ui.graphics.painter.Painter

data class IconWithText(
    val icon: Int,
    val text: String
)

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Int
)