package com.pablichj.study.compose.order

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun BlueRedTextCentered() {
    Column {
        CustomLayout(
            red = {
                IconButton({}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Localized description")
                }
            },
            centeredText = {
                Text("Centered text....")
            },
            blue = {
                IconButton({}) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                }
                IconButton({}) {
                    Icon(Icons.Filled.Email, contentDescription = "Localized description")
                }
            },
        )
        Divider(Modifier.height(2.dp))
        CustomLayout(
            red = {
                IconButton({}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Localized description")
                }
            },
            centeredText = {
                Text("Centered text....".repeat(5))
            },
            blue = {
                IconButton({}) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                }
                IconButton({}) {
                    Icon(Icons.Filled.Email, contentDescription = "Localized description")
                }
            },
        )
        Divider(Modifier.height(2.dp))
        CustomLayout(
            red = {
                IconButton({}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Localized description")
                }
            },
            centeredText = {
                Text("Centered text....".repeat(8))
            },
            blue = {
                IconButton({}) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                }
                IconButton({}) {
                    Icon(Icons.Filled.Email, contentDescription = "Localized description")
                }
                IconButton({}) {
                    Icon(Icons.Filled.Phone, contentDescription = "Localized description")
                }
                IconButton({}) {
                    Icon(Icons.Filled.Check, contentDescription = "Localized description")
                }
            },
        )
    }
}

@Composable
fun CustomLayout(
    red: @Composable RowScope.() -> Unit,
    centeredText: @Composable (textAlignment: TextAlign) -> Unit,
    blue: @Composable RowScope.() -> Unit,
    maxWidthPercentageCenteredTextShouldTake: Float = 0.5f,
) {
    Layout(
        content = {
            Row(
                Modifier
                    .layoutId("red")
                    .border(1.dp, Color.Red)) {
                red()
            }
            Row(
                Modifier
                    .layoutId("blue")
                    .border(1.dp, Color.Blue), horizontalArrangement = Arrangement.End) {
                blue()
            }
            Box(
                Modifier
                    .layoutId("text")
                    .border(1.dp, Color.Magenta)) {
                centeredText(TextAlign.Center)
            }
        },
    ) { measurables, constraints ->
        val maxWidthCenteredTextShouldTake =
            (constraints.maxWidth * maxWidthPercentageCenteredTextShouldTake).roundToInt()
        val centeredTextConstraints = constraints.copy(
            minWidth = 0,
            maxWidth = maxWidthCenteredTextShouldTake,
        )
        val centerAlignedTextPlaceable = measurables.first { it.layoutId == "text" }.measure(centeredTextConstraints)

        val redAndBlueWidth = (constraints.maxWidth - centerAlignedTextPlaceable.width) / 2
            .coerceAtLeast(0)
        val redAndBlueConstraints = constraints.copy(minWidth = 0, maxWidth = redAndBlueWidth)
        val redPlaceable = measurables.first { it.layoutId == "red" }.measure(redAndBlueConstraints)
        val bluePlaceable = measurables.first { it.layoutId == "blue" }.measure(redAndBlueConstraints)

        val layoutWidth = constraints.maxWidth
        val layoutHeight = listOf(centerAlignedTextPlaceable, redPlaceable, bluePlaceable)
            .maxOf { it.height }
            .coerceAtMost(constraints.maxHeight)

        layout(layoutWidth, layoutHeight) {
            redPlaceable.place(0, 0)
            centerAlignedTextPlaceable.place(
                (layoutWidth / 2) - (centerAlignedTextPlaceable.width / 2),
                (layoutHeight / 2) - (centerAlignedTextPlaceable.height / 2),
            )
            bluePlaceable.place(layoutWidth - bluePlaceable.width, 0)
        }
    }
}