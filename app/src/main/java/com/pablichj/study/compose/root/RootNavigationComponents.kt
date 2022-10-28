package com.pablichj.study.compose.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pablichj.study.compose.router.IRouterState

@Composable
fun NavigationDrawerRoot(
    modifier: Modifier = Modifier,
    rootState: IRootState,
    routerState: IRouterState,
    router: @Composable () -> Unit
) {
    DrawerNavigationComponent(
        modifier = modifier,
        onItemClick = { rootNode ->
            routerState.navigate(rootState.rootNavActions.getNavAction(rootNode))
        },
        content = router
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavigationComponent(
    modifier: Modifier,
    onItemClick: (rootNode: RootNode) -> Unit,
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
    onItemClick: (rootNode: RootNode) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(onClick = { onItemClick(RootNode.RootHomeGraph) }) {
            Text(text = "HOME")
        }

        Card(onClick = { onItemClick(RootNode.RootOrdersGraph) }) {
            Text(text = "ORDER")
        }

        Card(onClick = { onItemClick(RootNode.RootAccountGraph) }) {
            Text(text = "ACCOUNT")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContentModal(
    modifier: Modifier = Modifier,
    onItemClick: (rootNode: RootNode) -> Unit,
) {
    ModalDrawerSheet(modifier = modifier) {
        DrawerContentList(onItemClick = onItemClick)
    }
}