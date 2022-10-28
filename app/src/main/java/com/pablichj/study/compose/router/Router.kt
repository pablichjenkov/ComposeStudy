package com.pablichj.study.compose.router

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pablichj.study.compose.common.LifecycleEventObserver
import com.pablichj.study.compose.home.HomeGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class Node(val route: String)

@Composable
internal fun Router(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    routerState: IRouterState,
    rootGraphBuilder: NavGraphBuilder.(IRouterState) -> Unit,
) {
    var routerJob: Job? = remember { null }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeGraph.route,
    ) {
        rootGraphBuilder(routerState)
    }

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
            Log.d("Router", "Pablo new navigation event. navigateTo(${node.route})")
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