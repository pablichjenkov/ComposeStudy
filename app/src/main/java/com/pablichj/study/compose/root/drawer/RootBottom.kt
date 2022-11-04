package com.pablichj.study.compose.root.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pablichj.study.compose.root.IRootState


@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NavigationBottom(
    modifier: Modifier = Modifier,
    rootState: IRootState,
    Router: @Composable () -> Unit
) {
    val navItems by rootState.navItemsFlow.collectAsStateWithLifecycle(
        initialValue = emptyList(),
        lifecycle = LocalLifecycleOwner.current.lifecycle
    )

    /*
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9F)
                .background(Color.LightGray)
                .border(width = 4.dp, color = Color.Blue),
        ) {
            Router()
        }
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            navItems.forEach { navItemInfo ->
                NavigationBarItem(
                    selected = navItemInfo.selected,
                    onClick = {  rootState.navItemClick(navItemInfo) },
                    icon = {
                        Icon(
                            imageVector = navItemInfo.icon,
                            contentDescription = navItemInfo.label
                        )
                    }
                )
            }
        }
    }
    */
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(2.dp, Color.Blue),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Top Bar"
                )
            }
        },
        bottomBar = {
            BottomBar(navItems) { navItem ->
                rootState.navItemClick(navItem)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)){
            Router()
        }
    }

}

@Composable
private fun BottomBar(
    navItems: List<NavItemInfo>,
    onNavItemClick: (NavItemInfo) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        navItems.forEach { navItemInfo ->
            NavigationBarItem(
                selected = navItemInfo.selected,
                onClick = { onNavItemClick(navItemInfo) },
                icon = {
                    Icon(
                        imageVector = navItemInfo.icon,
                        contentDescription = navItemInfo.label
                    )
                }
            )
        }
    }
}