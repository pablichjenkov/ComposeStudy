package com.pablichj.study.compose.router

import com.pablichj.study.compose.common.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch

interface IRouterState

class RouterState(

) : IRouterState {

    // TODO: inject dispatchers
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var currentRoute: String? = null

    val _routeFlow = MutableSharedFlow<String>()
    val routeFlow: Flow<String> = _routeFlow.conflate()

    fun navigate(screen: Screen) {
        coroutineScope.launch { _routeFlow.emit(screen.route) }
    }

}