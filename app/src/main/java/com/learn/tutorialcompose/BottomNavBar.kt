package com.learn.tutorialcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Composable fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemCLick: (BottomNavItem) -> Unit
) {
    NavigationBar(
        containerColor = BottomNavColor,
        contentColor = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(100.dp)
    ) {
        items.forEach {
            val selected =
                it.route == navController.currentBackStackEntryAsState().value?.destination?.route
            
            NavigationBarItem(
                selected = selected,
                onClick = { onItemCLick(it) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = it.name
                        )
                    }
                },
                label = { if(selected) Text(it.name) },
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