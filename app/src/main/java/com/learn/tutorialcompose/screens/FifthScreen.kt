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
            text = """
                    AS AN ANDROID DEVELOPER
                    have been 1 years more learned frontend android
                    development.
                    I also like reading book such economic and self
                    improvement.
                    """.trimIndent(),
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