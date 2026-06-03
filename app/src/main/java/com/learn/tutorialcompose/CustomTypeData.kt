package com.learn.tutorialcompose

data class IconWithText(
    val icon: Int,
    val text: String
)

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Int
)

data class SwitchScreen(
    val name: String,
    val screen: () -> Unit
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