package com.pablichj.study.compose.root

import androidx.navigation.NavGraphBuilder
import com.pablichj.study.compose.account.accountGraph
import com.pablichj.study.compose.home.homeGraph
import com.pablichj.study.compose.order.orderGraph

internal fun NavGraphBuilder.rootGraph() {
    homeGraph(onTopButtonClick = { })
    orderGraph()
    accountGraph()
}