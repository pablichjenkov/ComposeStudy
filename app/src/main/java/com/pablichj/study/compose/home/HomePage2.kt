package com.pablichj.study.compose.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.pablichj.study.compose.router.Node

@Composable
fun HomePage2(
    modifier: Modifier = Modifier,
    homeState: IHomeState,
    toGoOnClick: Node
) {

    //var boxWithConstraints by remember { mutableStateOf(0f) }
    var boxWithConstraintsHeight by remember { mutableStateOf(0f) }
    var boxHeight = remember { 0f }

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
                .background(Color.Black),
            //verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val spacerHeight = boxWithConstraintsHeight.times(0.25)
            val spacerHeightDp = with(density) { spacerHeight.toInt().toDp() }
            Log.d("Pablo", "spacerHeightDp = ${spacerHeightDp}")

            val redBoxHeight = boxWithConstraintsHeight.times(0.75)
            val redBoxHeightDp = with(density) { redBoxHeight.toInt().toDp() }
            Log.d("Pablo", "redBoxHeightDp = ${redBoxHeightDp}")

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.75F)
                    .height(spacerHeightDp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth(0.5F)
                    .height(redBoxHeightDp)
                    .absoluteOffset(y = 100.dp)
                    .onGloballyPositioned { coordinates ->
                        boxHeight = (coordinates.size.height.toFloat())
                    }
            )
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