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

package com.pablichj.study.compose.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.Node

object OrdersGraph : Node("ordersGraph")
private object OrdersNode : Node("orders")

internal fun NavGraphBuilder.orderGraph(
    routerState: IRouterState
) {
    navigation(
        route = OrdersGraph.route,
        startDestination = OrdersNode.route
    ) {
        composable(OrdersNode.route) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BlueRedTextCentered()
            }
        }
    }
}