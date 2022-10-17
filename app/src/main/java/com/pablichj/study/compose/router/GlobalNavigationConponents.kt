package com.pablichj.study.compose.router

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pablichj.study.compose.common.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavigationComponent(
    modifier: Modifier,
    onItemClick: (screen: Screen) -> Unit,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        //drawerContent = { DrawerContentModal(modifier, onItemClick) },
        drawerContent = { DrawerContentList(modifier, onItemClick) },
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = DrawerDefaults.scrimColor,
        content = content
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContentList(
    modifier: Modifier = Modifier,
    onItemClick: (screen: Screen) -> Unit
) {
    Column(
        modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(onClick = { onItemClick(Screen.Home)}) {
            Text(text = "HOME")
        }

        Card(onClick = {onItemClick(Screen.Orders)}) {
            Text(text = "ORDER")
        }

        Card(onClick = {onItemClick(Screen.Account)}) {
            Text(text = "ACCOUNT")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContentModal(
    modifier: Modifier = Modifier,
    onItemClick: (screen: Screen) -> Unit
) {
    ModalDrawerSheet(modifier = modifier) {
        DrawerContentList(onItemClick = onItemClick)
    }
}