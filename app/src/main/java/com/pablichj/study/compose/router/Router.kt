package com.pablichj.study.compose.router

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pablichj.study.compose.account.accountGraph
import com.pablichj.study.compose.home.HomeNode
import com.pablichj.study.compose.home.homeGraph
import com.pablichj.study.compose.order.OrdersNode
import com.pablichj.study.compose.order.orderGraph

open class Node(val route: String)
object IgnoreNode : Node("IgnoreNode")

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun Router(
    modifier: Modifier = Modifier,
    routerState: RouterState // TODO use the interface IRouterState
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeNode.route
    ) {
        homeGraph(onTopButtonClick = { navController.navigate(OrdersNode.route) })
        orderGraph()
        accountGraph(navController)
    }

    val node: Node =
        routerState.nodeFlow.collectAsStateWithLifecycle(initialValue = IgnoreNode).value

    when (node) {
        IgnoreNode -> {
            // Ignore initial route = "Ignore initial state"
        }
        else -> {
            navController.navigate(node.route)
        }
    }

    DisposableEffect(key1 = Unit) {

        val destinationChangeListener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                routerState.currentRoute = destination.route
                Log.d("Router", "Pablo currentRoute = $destination.route")
            }

        navController.addOnDestinationChangedListener(destinationChangeListener)

        onDispose {
            Log.d("Router", "Pablo Disposing LaunchEffect with route = $node")
            navController.removeOnDestinationChangedListener(destinationChangeListener)
        }

    }

}
