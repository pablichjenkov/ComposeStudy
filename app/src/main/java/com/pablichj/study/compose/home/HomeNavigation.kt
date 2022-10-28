package com.pablichj.study.compose.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.Node

object HomeGraph : Node("homeGraph")
private object HomeNode1 : Node("home1")
private object HomeNode2 : Node("home2")

internal fun NavGraphBuilder.homeGraph(
    routerState: IRouterState,
    onTopButtonClick: () -> Unit
) {
    navigation(
        route = HomeGraph.route,
        startDestination = HomeNode1.route
    ) {
        composable(HomeNode1.route) { backStackEntry ->
            val homeViewModel = hiltViewModel<HomeStateViewModel>(backStackEntry)
            HomeRoute1(routerState, homeViewModel.homeState)
        }
        composable(HomeNode2.route) { backStackEntry ->
            val homeViewModel = hiltViewModel<HomeStateViewModel>(backStackEntry)
            HomeRoute2(routerState, homeViewModel.homeState)
        }
    }
}

@Composable
fun HomeRoute1(routerState: IRouterState, homeState: IHomeState) {
    HomePage1(
        homeState = homeState,
        level = 0,
        onClick = {
            routerState.navigate {
                it.navigate(HomeNode2.route)
            }
        }
    )
}

@Composable
fun HomeRoute2(
    routerState: IRouterState,
    homeState: IHomeState
) {
    HomePage2(
        homeState = homeState,
        onClick = {
            routerState.navigate { it.navigate(HomeNode1.route) }
        }
    )
}