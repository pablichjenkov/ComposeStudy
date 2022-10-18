package com.pablichj.study.compose.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablichj.study.compose.router.Node

object HomeNode : Node("home")

internal fun NavGraphBuilder.homeGraph(
    onTopButtonClick: () -> Unit
) {
    composable(HomeNode.route) { backStackEntry ->
        val homeViewModel = hiltViewModel<HomeStateViewModel>(backStackEntry)
        HomeRoute(homeViewModel.homeState, onTopButtonClick)
    }
}

@Composable
fun HomeRoute(homeState: IHomeState, onTopButtonClick: () -> Unit) {
    HomeScreen(homeState = homeState, onTopButtonClick = onTopButtonClick)
}