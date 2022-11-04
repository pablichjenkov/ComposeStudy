package com.pablichj.study.compose.effects

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.Node

object EffectsGraph : Node("nestedGraph")
private object EffectNode1 : Node("effectNode1")
private object EffectNode2 : Node("effectNode2")

internal fun NavGraphBuilder.effectsGraph(routerState: IRouterState) {
    navigation(
        route = EffectsGraph.route,
        startDestination = EffectNode1.route
    ) {
        composable(EffectNode1.route) { navBackstackEntry ->
            EffectsPage1(100) {
                routerState.navigate { it.navigate(EffectNode2.route) }
            }
        }
        composable(EffectNode2.route) { navBackstackEntry ->
            EffectPage2()
        }
    }
}