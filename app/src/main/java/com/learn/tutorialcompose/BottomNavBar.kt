package com.learn.tutorialcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.learn.tutorialcompose.ui.theme.BottomNavColor
import com.learn.tutorialcompose.ui.theme.BottomNavIconColor


@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemCLick: (BottomNavItem) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        containerColor = BottomNavColor,
        contentColor = Color.White,
        windowInsets = WindowInsets(0, 0, 0, 0),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .height(80.dp)
    ) {
        items.forEach {
            val selected = it.destination.route == currentRoute
            NavigationBarItem(
                selected = selected,
                onClick = { onItemCLick(it) },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.label
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = BottomNavIconColor,
                    unselectedIconColor = Color.White,
                    selectedTextColor = BottomNavIconColor,
                    unselectedTextColor = Color.White
                )
            )
        }
    }
}