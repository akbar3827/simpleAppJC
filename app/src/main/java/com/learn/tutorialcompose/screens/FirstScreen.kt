package com.learn.tutorialcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.learn.tutorialcompose.MyViewModel
import com.learn.tutorialcompose.ui.theme.BgTextField
import com.learn.tutorialcompose.ui.theme.BottomNavIconColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination<RootGraph>
@Composable
fun FirstScreen(
    navigator: DestinationsNavigator,
    vm: MyViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 40.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "back to home screen",
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navigator.popBackStack()
                }.padding(horizontal = 10.dp)
            )
        }
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (textField,
                inputButton) = createRefs()

            BasicTextField(
                value = vm.textFieldState2.value,
                singleLine = true,
                onValueChange = { vm.textFieldState2.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BgTextField)
                    .padding(
                        start = 12.dp,
                        end = 60.dp,
                        top = 14.dp,
                        bottom = 15.dp
                    )
                    .constrainAs(textField) {
                        top.linkTo(parent.top, margin = 50.dp)
                    },
                cursorBrush = SolidColor(Color.Black.copy(alpha = 0.3f)),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                decorationBox = { innerTextField ->
                    if (vm.textFieldState2.value.isEmpty()) {
                        Text(
                            text = "Name",
                            color = Color.Gray.copy(alpha = 0.7f),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
            Button(
                modifier = Modifier
                    .height(48.dp)
                    .width(60.dp).constrainAs(inputButton) {
                        top.linkTo(textField.top)
                        end.linkTo(textField.end, margin = 25.dp)
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BottomNavIconColor,
                    contentColor = Color.White,
                    disabledContainerColor = BottomNavIconColor,
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "enter",
                    tint = Color.White,
                    modifier = Modifier.scale(-1f, 1f)
                )
            }
        }
    }
}