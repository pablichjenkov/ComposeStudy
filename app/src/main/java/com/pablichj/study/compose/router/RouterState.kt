package com.pablichj.study.compose.router

import androidx.navigation.NavController
import com.pablichj.study.compose.common.DispatchersBin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch

typealias NavActionLambda = (NavController) -> Unit

interface IRouterState {
    var startDestination: String
    val currentRouteFlow: Flow<String>
    val navActionFlow: Flow<NavActionLambda>
    fun navigate(navActionLambda: NavActionLambda)
    fun dispatchNewRouter(newRoute: String)
}

class RouterState(
    override var startDestination: String,
    val dispatchersBin: DispatchersBin
) : IRouterState {

    private val coroutineScope = CoroutineScope(dispatchersBin.main)

    private val _currentRouteFlow = MutableStateFlow<String>(startDestination)
    override val currentRouteFlow: Flow<String> =_currentRouteFlow

    private val _navActionFlow = MutableSharedFlow<NavActionLambda>()
    override val navActionFlow: Flow<NavActionLambda> =
        _navActionFlow.conflate() // conflate() Alleviates screen updates too fast

    override fun navigate(navActionLambda: NavActionLambda) {
        coroutineScope.launch { _navActionFlow.emit(navActionLambda) }
    }

    override fun dispatchNewRouter(newRoute: String) {
        _currentRouteFlow.value = newRoute
    }

}