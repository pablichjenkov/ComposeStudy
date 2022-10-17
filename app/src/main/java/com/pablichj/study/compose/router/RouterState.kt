package com.pablichj.study.compose.router

import com.pablichj.study.compose.common.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

interface IRouterState

class RouterState(

) : IRouterState {

    // TODO: inject dispatchers
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val routeFlow: MutableStateFlow<String> = MutableStateFlow("Initial State Ignore")

    fun navigate(screen: Screen) {
        coroutineScope.launch { routeFlow.emit(screen.route) }
    }

}