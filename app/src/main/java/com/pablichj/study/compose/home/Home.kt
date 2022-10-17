package com.pablichj.study.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Home(
    modifier: Modifier = Modifier,
    homeState: IHomeState,
    onTopButtonClick: () -> Unit
) {

    val homeStateName = remember { mutableStateOf(homeState.name) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.size(40.dp))
        Text(text = "Home Screen")
        Spacer(Modifier.size(40.dp))
        Button(
            onClick = {
                onTopButtonClick()
            }
        ) {
            Text(text = "Home State = ${homeStateName.value}")
        }
    }
}