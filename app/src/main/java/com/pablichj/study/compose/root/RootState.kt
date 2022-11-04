package com.pablichj.study.compose.root

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import com.pablichj.study.compose.common.DispatchersBin
import com.pablichj.study.compose.data.Interactor
import com.pablichj.study.compose.root.drawer.NavItemInfo
import com.pablichj.study.compose.router.IRouterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootStateStateViewModel @Inject constructor(
    val rootState: IRootState,
    val routerState: IRouterState
) : ViewModel()

interface IRootState {
    val navItemsFlow: Flow<List<NavItemInfo>>
    val drawerOpenFlow: Flow<Boolean>
    fun setRouterState(routerState: IRouterState)
    fun navItemClick(navItemInfo: NavItemInfo)
}

class RootState @Inject constructor(
    private val rootNavActions: RootNavActions,
    val interactor: Interactor,
    val dispatchersBin: DispatchersBin
) : IRootState {

    private val coroutineScope = CoroutineScope(dispatchersBin.main)
    private lateinit var routerState: IRouterState

    private val _navItemsFlow = MutableStateFlow<List<NavItemInfo>>(emptyList())
    override val navItemsFlow: Flow<List<NavItemInfo>>
        get() = _navItemsFlow

    private val _drawerOpenFlow = MutableSharedFlow<Boolean>()
    override val drawerOpenFlow: Flow<Boolean>
        get() = _drawerOpenFlow

    var navItems: List<NavItemInfo>

    init {
        navItems = listOf(
            NavItemInfo(
                label = "Home",
                icon = Icons.Filled.Home,
                rootNode = RootNode.RootHomeGraph,
                selected = true
            ),
            NavItemInfo(
                label = "Layout",
                icon = Icons.Filled.Reorder,
                rootNode = RootNode.RootOrdersGraph,
                selected = false
            ),
            NavItemInfo(
                label = "Offset",
                icon = Icons.Filled.ManageAccounts,
                rootNode = RootNode.RootAccountGraph,
                selected = false
            ),
            NavItemInfo(
                label = "Effects",
                icon = Icons.Filled.NestCamWiredStand,
                rootNode = RootNode.RootEffectsGraph,
                selected = false
            ),
            NavItemInfo(
                label = "Activity",
                icon = Icons.Filled.Launch,
                rootNode = RootNode.RootActivityGraph,
                selected = false
            )
        )
        _navItemsFlow.value = navItems
    }

    override fun setRouterState(routerState: IRouterState) {
        this.routerState = routerState
        observeRouter()
    }

    private fun observeRouter() {
        coroutineScope.launch {
            routerState.currentRouteFlow.collect { currentRoute ->
                Log.d("observeRouter","currentRoute = $currentRoute")
                /*navItems.firstOrNull {  navItem ->
                    navItem.rootNode == currentRoute
                }?.let { currentNavItem ->
                    setDrawerSelectedItem(currentNavItem)
                }*/
            }
        }
    }

    override fun navItemClick(navItemInfo: NavItemInfo) {
        coroutineScope.launch {
            _drawerOpenFlow.emit(false)

            // Switch drawer selected item
            setDrawerSelectedItem(navItemInfo)

            val navAction = rootNavActions.getNavAction(navItemInfo.rootNode)
            routerState.navigate(navAction)
        }
    }

    private suspend fun setDrawerSelectedItem(navItemInfo: NavItemInfo) {
        navItems = navItems.map {
            it.copy().apply { selected = navItemInfo.rootNode == it.rootNode }
        }
        _navItemsFlow.emit(navItems)
    }

}