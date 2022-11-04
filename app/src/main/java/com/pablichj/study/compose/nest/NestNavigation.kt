package com.pablichj.study.compose.nest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.Node

object NestedGraph : Node("nestedGraph")
private object NestedNodeHost : Node("nestedNodeHost")
private object NestNode1 : Node("nestedNode2")
private object NestNode2 : Node("nestedNode3")

internal fun NavGraphBuilder.nestGraph(routerState: IRouterState) {
    navigation(
        route = NestedGraph.route,
        startDestination = NestedNodeHost.route
    ) {
        composable(NestedNodeHost.route) { navBackstackEntry ->
            val navControllerNested = rememberNavController()
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navControllerNested,
                startDestination = NestNode1.route,
            ) {
                composable(NestNode1.route) { navBackstackEntry ->
                    NestNode1(navControllerNested)
                }
                composable(NestNode2.route) { navBackstackEntry ->
                    NestNode2(navControllerNested)
                }
            }
        }
    }
}

@Composable
fun NestNode1(navController: NavController) {
    Text(
        modifier = Modifier.clickable {
            navController.navigate(NestNode2.route)
        },
        text = "Hi!, I am inside Nested Route 1"
    )
}

@Composable
fun NestNode2(navController: NavController) {
    Text(
        modifier = Modifier.clickable {
            //navController.navigate(NestNode2.route)
        },
        text = "Hi!, I am inside Nested Route 2"
    )
}