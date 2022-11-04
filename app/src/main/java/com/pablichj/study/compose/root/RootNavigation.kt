package com.pablichj.study.compose.root

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.activity
import com.pablichj.study.compose.MainActivity
import com.pablichj.study.compose.account.AccountGraph
import com.pablichj.study.compose.account.accountGraph
import com.pablichj.study.compose.effects.EffectsGraph
import com.pablichj.study.compose.effects.effectsGraph
import com.pablichj.study.compose.home.HomeGraph
import com.pablichj.study.compose.home.homeGraph
import com.pablichj.study.compose.order.OrdersGraph
import com.pablichj.study.compose.order.orderGraph
import com.pablichj.study.compose.router.IRouterState
import javax.inject.Inject

enum class RootNavigationType {
    Drawer,
    BottomBar
}

sealed class RootNode {
    object RootHomeGraph : RootNode()
    object RootOrdersGraph : RootNode()
    object RootAccountGraph : RootNode()
    object RootEffectsGraph : RootNode()
    object RootActivityGraph : RootNode()
}

fun NavGraphBuilder.rootGraph(routerState: IRouterState) {
    homeGraph(routerState, onTopButtonClick = { })
    orderGraph(routerState)
    accountGraph(routerState)
    effectsGraph(routerState)
    this.activity("MainActivityRoute") {
        this.activityClass = MainActivity::class
    }
}

class RootNavActions @Inject constructor() {

    fun getNavAction(rootNode: RootNode): (navController: NavController) -> Unit {
        return when (rootNode) {
            RootNode.RootHomeGraph -> navigateToHomeGraph
            RootNode.RootAccountGraph -> navigateToAccountGraph
            RootNode.RootOrdersGraph -> navigateToOrdersGraph
            RootNode.RootActivityGraph -> navigateToActivity
            RootNode.RootEffectsGraph -> navigateToEffectsGraph
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
    val navigateToEffectsGraph: (navController: NavController) -> Unit = { navController ->
        navController.navigate(EffectsGraph.route)  {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToActivity: (navController: NavController) -> Unit = { navController ->
        navController.navigate("MainActivityRoute")
    }

}