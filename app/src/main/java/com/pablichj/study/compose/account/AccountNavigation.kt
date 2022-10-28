package com.pablichj.study.compose.account

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pablichj.study.compose.home.HomeGraph
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.Node

object AccountGraph : Node("accountGraph")
private object AccountNode : Node("accountNode")

internal fun NavGraphBuilder.accountGraph(routerState: IRouterState) {
    navigation(
        route = AccountGraph.route,
        startDestination = AccountNode.route
    ) {
        composable(AccountNode.route) { navBackstackEntry ->
            val accountStateViewModel = hiltViewModel<AccountStateViewModel>(navBackstackEntry)
            val accountState = accountStateViewModel.accountState
            AccountPage(
                accountState = accountState,
                onResult = {
                    routerState.navigate(HomeGraph)
                }
            )
        }
    }
}