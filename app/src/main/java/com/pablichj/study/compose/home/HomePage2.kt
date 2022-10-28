package com.pablichj.study.compose.home

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.pablichj.study.compose.router.Node

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomePage2(
    modifier: Modifier = Modifier,
    homeState: IHomeState,
    onClick: () -> Unit
) {

    //var boxWithConstraints by remember { mutableStateOf(0f) }
    var boxWithConstraintsHeight by remember { mutableStateOf(0f) }
    var boxHeight = remember { 0f }

    val infiniteTransition = rememberInfiniteTransition()

    // Create a value that is altered by the transition based on the configuration. We use
    // the animated float value the returns and updates a float from the initial value to
    // target value and repeats it (as its called on the infititeTransition).
    /*val heightAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = boxWithConstraintsHeight,
        animationSpec = infiniteRepeatable(
            animation = tween<Float>(
                durationMillis = 3000,
                easing = FastOutLinearInEasing,
            ),
            repeatMode = RepeatMode.Reverse
        )
    )*/

    val density = LocalDensity.current

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                boxWithConstraintsHeight = (coordinates.size.height.toFloat())
            }
    ) { // boxWithConstraintsHeightMax could have been obtained from boxConstraintScope but the
        // value is pre-layout so we are not sure whether the Parent will fill the whole size or nor.
        val boxConstraintScope = this

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.Black),
            //verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val spacerHeight = boxWithConstraintsHeight.times(0.25)
            val spacerHeightDp = with(density) { spacerHeight.toInt().toDp() }
            Log.d("Pablo", "spacerHeightDp = ${spacerHeightDp}")

            val blueBoxHeight = boxWithConstraintsHeight.times(1.5)
            val blueBoxHeightDp = with(density) { blueBoxHeight.toInt().toDp() }
            Log.d("Pablo", "bluBoxHeightDp = ${blueBoxHeightDp}")

            val redBoxHeight = boxWithConstraintsHeight.times(0.75)
            val redBoxHeightDp = with(density) { redBoxHeight.toInt().toDp() }
            Log.d("Pablo", "redBoxHeightDp = ${redBoxHeightDp}")

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.75F)
                    .height(spacerHeightDp)
                    .offset(x = spacerHeightDp)
                    .background(Color.Green)
            )
            Row(
                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxWidth()
                    //.horizontalScroll(rememberScrollState())
                    .height(blueBoxHeightDp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.15F)
                        .height(spacerHeightDp)
                        .offset(x = 10.dp, y = 10.dp)// offset() respect to the parent, not like Box
                        .background(Color.Gray)
                )
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .fillMaxWidth(0.5F)
                        .height(redBoxHeightDp)
                        // offset() in a Box, refers to Child offset(), more like a padding for its child
                        .offset(x = 80.dp, y = spacerHeightDp)
                        .onGloballyPositioned { coordinates ->
                            boxHeight = (coordinates.size.height.toFloat())
                        }
                ) {
                    Text(text = "Pablo")
                }
                Column(
                    modifier = Modifier
                        .background(Color.Yellow)
                        .fillMaxWidth()
                ) {
                    var count by remember { mutableStateOf(0) }
                    Button(
                        onClick = { count++ }
                    ) {
                        Text("Add")
                    }
                    AnimatedContent(targetState = count) { targetCount ->
                        // Make sure to use `targetCount`, not `count`.
                        Text(text = "Count: $targetCount")
                    }
                }
            }
        }
        /* This work with "Row and Column" but the fraction is related to the size that is left to
        fill not the parent size
        Spacer(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5F)
        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth(0.5F)
                .fillMaxHeight(0.5F)
        )*/
    }
}