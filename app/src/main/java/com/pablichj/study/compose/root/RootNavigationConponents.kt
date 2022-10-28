package com.pablichj.study.compose.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pablichj.study.compose.account.AccountGraph
import com.pablichj.study.compose.home.HomeGraph
import com.pablichj.study.compose.order.OrdersGraph
import com.pablichj.study.compose.router.Node

@Composable
fun NavigationDrawerRoot(
    modifier: Modifier = Modifier,
    rootState: IRootState,
    router: @Composable () -> Unit
) {
    val routerState = rootState.routerState

    DrawerNavigationComponent(
        modifier = modifier,
        onItemClick = { globalScreen ->
            routerState.navigate(globalScreen)
        },
        content = router
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavigationComponent(
    modifier: Modifier,
    onItemClick: (node: Node) -> Unit,
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
    onItemClick: (node: Node) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(onClick = { onItemClick(HomeGraph) }) {
            Text(text = "HOME")
        }

        Card(onClick = { onItemClick(OrdersGraph) }) {
            Text(text = "ORDER")
        }

        Card(onClick = { onItemClick(AccountGraph) }) {
            Text(text = "ACCOUNT")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContentModal(
    modifier: Modifier = Modifier,
    onItemClick: (node: Node) -> Unit
) {
    ModalDrawerSheet(modifier = modifier) {
        DrawerContentList(onItemClick = onItemClick)
    }
}