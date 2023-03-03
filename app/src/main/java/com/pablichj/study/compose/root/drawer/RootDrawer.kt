package com.pablichj.study.compose.root.drawer

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pablichj.study.compose.root.IRootState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    rootState: IRootState,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContentModal(modifier, rootState)
        },
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = DrawerDefaults.scrimColor,
        content = content
    )

    LaunchedEffect(key1 = rootState) {
        launch {
            rootState.drawerOpenFlow.collect { drawerOpen ->
                Log.d("DrawerNavigationComponent", "drawerOpen = $drawerOpen")
                if (!drawerOpen) {
                    drawerState.close()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun DrawerContentModal(
    modifier: Modifier = Modifier,
    rootState: IRootState
) {
    val navItems by rootState.navItemsFlow.collectAsStateWithLifecycle(
        initialValue = emptyList(),
        lifecycle = LocalLifecycleOwner.current.lifecycle
    )

    ModalDrawerSheet(modifier = modifier) {
        Column {
            DrawerLogo(
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)
            )
            DrawerContentList(
                navItems = navItems,
                onNavItemClick = { navItem -> rootState.navItemClick(navItem) }
            )
        }
    }
}

@Composable
private fun DrawerLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painterResource(com.pablichj.study.compose.R.drawable.ic_launcher_foreground),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            painter = painterResource(com.pablichj.study.compose.R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(com.pablichj.study.compose.R.string.app_name),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContentList(
    modifier: Modifier = Modifier,
    navItems: List<NavItemInfo>,
    onNavItemClick: (NavItemInfo) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (navItem in navItems) {
            NavigationDrawerItem(
                label = { Text(navItem.label) },
                icon = { Icon(navItem.icon, null) },
                selected = navItem.selected,
                onClick = { onNavItemClick(navItem) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}