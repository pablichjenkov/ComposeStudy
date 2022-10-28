package com.pablichj.study.compose.root

import androidx.navigation.NavGraphBuilder
import com.pablichj.study.compose.account.accountGraph
import com.pablichj.study.compose.home.homeGraph
import com.pablichj.study.compose.order.orderGraph
import com.pablichj.study.compose.router.IRouterState

fun NavGraphBuilder.rootGraph(routerState: IRouterState) {
    homeGraph(routerState, onTopButtonClick = { })
    orderGraph(routerState)
    accountGraph(routerState)
}