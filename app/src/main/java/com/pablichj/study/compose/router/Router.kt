package com.pablichj.study.compose.router

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pablichj.study.compose.common.lifecycleEventObserver
import com.pablichj.study.compose.home.HomeNode
import com.pablichj.study.compose.root.rootGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class Node(val route: String)

@Composable
internal fun Router(
    modifier: Modifier = Modifier,
    routerState: IRouterState // TODO use the interface IRouterState
) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    var routerJob: Job? = null

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeNode.route
    ) {
        rootGraph()
    }

    lifecycleEventObserver(
        lifecycleOwner = LocalLifecycleOwner.current,
        onStart = {
            routerJob = coroutineScope.launch {
                collectRoutes(coroutineScope, navController, routerState)
            }
        },
        onStop = {
            routerJob?.cancel()
        }
    )
}

private suspend fun collectRoutes(
    coroutineScope: CoroutineScope,
    navController: NavController,
    routerState: IRouterState
): Job {
    Log.d("Router", "Pablo starting collectRoutes()")
    
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
                "Pablo Disposing LaunchEffect with route = ${routerState.currentRoute}\n" +
                        "Throwable = ${th}"
            )
            navController.removeOnDestinationChangedListener(destinationChangeListener)
        }
    }
}