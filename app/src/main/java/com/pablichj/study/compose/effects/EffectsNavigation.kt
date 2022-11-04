package com.pablichj.study.compose.effects

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            EffectsLambdaCapturePage(100)
        }
        composable(EffectNode2.route) { navBackstackEntry ->
            EffectPage2()
        }
    }
}

@Composable
fun EffectPage2() {
    Text(
        modifier = Modifier.clickable {
            //navController.navigate(NestNode2.route)
        },
        text = "Hi!, I am inside Effect Route 2"
    )
}