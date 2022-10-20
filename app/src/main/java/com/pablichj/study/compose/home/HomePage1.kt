package com.pablichj.study.compose.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pablichj.study.compose.router.Node

@Composable
fun HomePage1(
    modifier: Modifier = Modifier,
    homeState: IHomeState,
    level: Int,
    toGoOnClick: Node
) {

    val homeStateName = remember { mutableStateOf(homeState.name) }

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.size(40.dp))
        Text(text = "Home Screen level: $level")
        Spacer(Modifier.size(40.dp))
        Button(
            onClick = {
                homeState.routerState.navigate(toGoOnClick)
            }
        ) {
            Text(text = "Home State = ${homeStateName.value}")
        }
    }
}