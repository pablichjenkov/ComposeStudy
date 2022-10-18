package com.pablichj.study.compose.router

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch

interface IRouterState

class RouterState(

) : IRouterState {

    // TODO: inject dispatchers
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var currentRoute: String? = null

    val _nodeFlow = MutableSharedFlow<Node>()
    val nodeFlow: Flow<Node> = _nodeFlow.conflate()

    fun navigate(node: Node) {
        coroutineScope.launch { _nodeFlow.emit(node) }
    }

}