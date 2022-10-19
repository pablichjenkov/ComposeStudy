package com.pablichj.study.compose.router

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pablichj.study.compose.common.LifecycleEventObserver
import com.pablichj.study.compose.home.HomeNode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class Node(val route: String)

@Composable
internal fun Router(
    modifier: Modifier = Modifier,
    routerState: IRouterState, // TODO use the interface IRouterState
    rootGraphBuilder: NavGraphBuilder.() -> Unit
) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    var routerJob: Job? = remember { null }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeNode.route,
        builder = rootGraphBuilder
    )

    LifecycleEventObserver(
        lifecycleOwner = LocalLifecycleOwner.current,
        onStart = {
            routerJob = collectRoutes(coroutineScope, navController, routerState)
        },
        onStop = {
            routerJob?.cancel()
        }
    )
}

private fun collectRoutes(
    coroutineScope: CoroutineScope,
    navController: NavController,
    routerState: IRouterState
): Job {
    val destinationChangeListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            routerState.currentRoute = destination.route
            Log.d("Router", "Pablo currentRoute = ${destination.route}")
        }

    navController.addOnDestinationChangedListener(destinationChangeListener)

    return coroutineScope.launch {
        routerState.nodeFlow.collect { node ->
            navController.navigate(node.route)
        }
    }.apply {
        invokeOnCompletion { th ->
            Log.d(
                "Router",
                "Pablo Disposing routerJob\n" +
                        "Throwable = $th"
            )
            navController.removeOnDestinationChangedListener(destinationChangeListener)
        }
    }
}