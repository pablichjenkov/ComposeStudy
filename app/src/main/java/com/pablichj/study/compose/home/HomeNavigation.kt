package com.pablichj.study.compose.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablichj.study.compose.order.OrdersNode
import com.pablichj.study.compose.router.Node

object HomeNode1 : Node("home1")
object HomeNode2 : Node("home2")

internal fun NavGraphBuilder.homeGraph(
    onTopButtonClick: () -> Unit
) {
    composable(HomeNode1.route) { backStackEntry ->
        val homeViewModel = hiltViewModel<HomeStateViewModel>(backStackEntry)
        HomeRoute(homeViewModel.homeState)
    }
    homeGraph2()
}

@Composable
fun HomeRoute(homeState: IHomeState) {
    HomePage1(homeState = homeState, level = 0, toGoOnClick =  HomeNode2)
}

internal fun NavGraphBuilder.homeGraph2() {
    composable(HomeNode2.route) { backStackEntry ->
        val homeViewModel = hiltViewModel<HomeStateViewModel>(backStackEntry)
        HomeRoute2(homeViewModel.homeState)
    }
}

@Composable
fun HomeRoute2(homeState: IHomeState) {
    HomePage2(homeState = homeState, toGoOnClick =  OrdersNode)
}