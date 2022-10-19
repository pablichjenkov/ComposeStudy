package com.pablichj.study.compose.router

import com.pablichj.study.compose.common.DispatchersBin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch

interface IRouterState {
    var currentRoute: String?
    val nodeFlow: Flow<Node>
    fun navigate(node: Node)
}

class RouterState(
    val dispatchersBin: DispatchersBin
) : IRouterState {

    private val coroutineScope = CoroutineScope(dispatchersBin.main)

    override var currentRoute: String? = null

    private val _nodeFlow = MutableSharedFlow<Node>()
    override val nodeFlow: Flow<Node> =
        _nodeFlow.conflate() // conflate() Alleviates screen updates too fast

    override fun navigate(node: Node) {
        coroutineScope.launch { _nodeFlow.emit(node) }
    }

}