package com.pablichj.study.compose.effects

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EffectsPage1(inputParam: Int, onGotoPage2Click: () -> Unit) {

    var counter by remember {
        mutableStateOf(0)
    }

    /**
     * Remember the value produced by calculation. calculation will only be evaluated during
     * the composition. "Recomposition will always return the value produced by composition".
     * In other words, remember without State, only save the result of the value in the first
     * composition. Any update to the value after that, is not "remembered", has no effect.
     * */
    var counterRememberOnly = remember { 0 } // almost the same as var counterRememberOnly = 0

    Log.d(
        "EffectsLambdaCapturePage",
        "Recomposing EffectsLambdaCapturePage:\n counter=$counter, counterRememberOnly=$counterRememberOnly, inputParam=$inputParam"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Log.d("EffectsLambdaCapturePage", "Recomposing Column, counter = $counter")
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
        Button(
            modifier = Modifier
                .offset(x = (-80).dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                counter += 1
                // It doesn't do anything, remember {} only save the value of the first composition.
                // It won't update in subsequent recompositions.
                counterRememberOnly += 2
            }
        ) {
            Text(text = "Trigger Recomposition")
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
        Text(text = "Remembered counter = $counter")
        Text(text = "counterRememberOnly = $counterRememberOnly")
        Text(text = "inputParam = $inputParam")
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
        if (counter < 4) {
            EffectComposable(countText = counter.toString(), counterRememberOnly)
        } else {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { onGotoPage2Click() }
            ) {
                Text(text = "Go to Effects Page 2")
            }
        }
    }
}

@Composable
fun ColumnScope.EffectComposable(countText: String, counterRememberOnly: Int) {
    Log.d(
        "EffectsLambdaCapturePage",
        "Recomposing EffectComposable:\n countText = $countText, counterRememberOnly=$counterRememberOnly"
    )

    val countTextRememberUpdated by rememberUpdatedState(newValue = countText)

    Text(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        text = "EffectComposable, countText = $countText"
    )
    DisposableEffect(key1 = Unit) {
        Log.d("EffectsLambdaCapturePage", "Inside EffectComposable.block, countText = $countText")
        Log.d(
            "EffectsLambdaCapturePage",
            "Inside EffectComposable.block, countTextRememberUpdated = $countTextRememberUpdated"
        )
        onDispose {
            Log.d("EffectsLambdaCapturePage", "Disposing EffectComposable, countText = $countText")
            Log.d(
                "EffectsLambdaCapturePage",
                "Disposing EffectComposable, countTextRememberUpdated = $countTextRememberUpdated"
            )
        }
    }
}

