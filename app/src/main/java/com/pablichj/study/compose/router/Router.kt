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
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = routerState.startDestination,
    ) {
        rootGraphBuilder(routerState)
    }

    // We don't use collectAsStateWithLifecycle() because that function implies to set an initial
    // value everytime this Composable underlying lifecycleOwner enters onStart. That behavior
    // creates duplicate destinations, and we don't want the distinctUntilChanged() behavior either
    // because we want to allow to push screens of the same type on top of each other.
    LifecycleEventObserver(
        lifecycleOwner = LocalLifecycleOwner.current,
        onStart = {
            routerState.routerJob = collectRoutes(coroutineScope, navController, routerState)
        },
        onStop = {
            routerState.routerJob?.cancel()
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
            routerState.dispatchNewRouter(destination.route ?: destination.displayName)
            navController.backQueue.fold("") { acc, navBackStackEntry ->
                val route = navBackStackEntry.destination.route
                    ?: navBackStackEntry.destination.id
                "${acc}/$route"
            }.also { Log.d("Router", "BackStack: $it") }
        }
    Log.d("Router", "Pablo Adding addOnDestinationChangedListener")
    navController.addOnDestinationChangedListener(destinationChangeListener)

    return coroutineScope.launch {
        routerState.navActionFlow.collect { navActionLambda ->
            navActionLambda(navController)
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