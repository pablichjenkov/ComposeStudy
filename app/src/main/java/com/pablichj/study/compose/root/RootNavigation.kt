package com.pablichj.study.compose.root

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import com.pablichj.study.compose.account.AccountGraph
import com.pablichj.study.compose.account.accountGraph
import com.pablichj.study.compose.home.HomeGraph
import com.pablichj.study.compose.home.homeGraph
import com.pablichj.study.compose.order.OrdersGraph
import com.pablichj.study.compose.order.orderGraph
import com.pablichj.study.compose.router.IRouterState
import javax.inject.Inject

sealed class RootNode {
    object RootHomeGraph : RootNode()
    object RootOrdersGraph : RootNode()
    object RootAccountGraph : RootNode()
}

fun NavGraphBuilder.rootGraph(routerState: IRouterState) {
    homeGraph(routerState, onTopButtonClick = { })
    orderGraph(routerState)
    accountGraph(routerState)
}

class RootNavActions @Inject constructor() {

    fun getNavAction(rootNode: RootNode): (navController: NavController) -> Unit {
        return when (rootNode) {
            RootNode.RootAccountGraph -> navigateToAccountGraph
            RootNode.RootHomeGraph -> navigateToHomeGraph
            RootNode.RootOrdersGraph -> navigateToOrdersGraph
        }
    }

    val navigateToHomeGraph: (navController: NavController) -> Unit = { navController ->
        navController.navigate(HomeGraph.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = false
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToOrdersGraph: (navController: NavController) -> Unit = { navController ->
        navController.navigate(OrdersGraph.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToAccountGraph: (navController: NavController) -> Unit = { navController ->
        navController.navigate(AccountGraph.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }
    }

}