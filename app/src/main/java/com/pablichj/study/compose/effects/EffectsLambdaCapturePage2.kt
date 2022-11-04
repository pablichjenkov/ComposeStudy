package com.pablichj.study.compose.effects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EffectPage2() {
    var timer by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text("Time $timer")
    }

    // Uncomment for demo only, don't commit the changes
    /*SideEffect {
        Thread.sleep(1000)
        timer++
    }*/

    // During composition updating a State does not trigger recomposition again.
    // The State value gets updated but it doesn't trigger recomposition. Uncomment above code to
    // trigger recomposition
    timer+=10

}