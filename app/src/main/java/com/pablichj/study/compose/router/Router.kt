/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pablichj.study.compose.router

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pablichj.study.compose.account.accountGraph
import com.pablichj.study.compose.common.Screen
import com.pablichj.study.compose.home.homeGraph
import com.pablichj.study.compose.order.orderGraph

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
        startDestination = Screen.Home.route
    ) {
        homeGraph(onTopButtonClick = { navController.navigate(Screen.Orders.route) })
        orderGraph()
        accountGraph(navController)
    }

    val route = routerState
        .routeFlow.collectAsStateWithLifecycle("Ignore initial state").value

    DisposableEffect(key1 = route) {

        navController.addOnDestinationChangedListener { controller: NavController,
                                                        destination: NavDestination,
                                                        arguments: Bundle? ->
            routerState.currentRoute = destination.route
        }

        if (navController.currentDestination?.route != route) {
            when (route) {
                Screen.Home.route,
                Screen.Orders.route,
                Screen.Account.route -> {
                    navController.navigate(route)
                }
                else -> {
                    // Ignore initial route = "Ignore initial state"
                }
            }
        } else {
            Log.d("Router", "Pablo nextRoute = currentRoute = $route")
        }

        onDispose {
            Log.d("Router", "Pablo Disposing LaunchEffect with route = $route")
        }

    }

}
