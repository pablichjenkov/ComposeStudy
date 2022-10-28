package com.pablichj.study.compose.account

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.Node

object AccountGraph : Node("accountGraph")
private object AccountNode1 : Node("accountNode1")
private object AccountNode2 : Node("accountNode2")

internal fun NavGraphBuilder.accountGraph(routerState: IRouterState) {
    navigation(
        route = AccountGraph.route,
        startDestination = AccountNode1.route
    ) {
        composable(AccountNode1.route) { navBackstackEntry ->
            val accountStateViewModel = hiltViewModel<AccountStateViewModel>(navBackstackEntry)
            val accountState = accountStateViewModel.accountState
            AccountRoute1(routerState, accountState)
        }
        composable(AccountNode2.route) { navBackstackEntry ->
            val accountStateViewModel = hiltViewModel<AccountStateViewModel>(navBackstackEntry)
            val accountState = accountStateViewModel.accountState
            AccountRoute2(routerState, accountState)
        }
    }
}

@Composable
fun AccountRoute1(routerState: IRouterState, accountState: IAccountState) {
    AccountPage(
        accountState = accountState,
        onResult = {
            routerState.navigate {
                it.navigate(AccountNode2.route)
            }
        }
    )
}

@Composable
fun AccountRoute2(routerState: IRouterState, accountState: IAccountState) {
    Text(text = "Hi!, I am inside Account Route 2")
}