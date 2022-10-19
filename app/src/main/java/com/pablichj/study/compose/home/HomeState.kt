package com.pablichj.study.compose.home

import androidx.lifecycle.ViewModel
import com.pablichj.study.compose.common.DispatchersBin
import com.pablichj.study.compose.data.Interactor
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.Node
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeStateViewModel @Inject constructor(val homeState: IHomeState) : ViewModel()

interface IHomeState {
    val name: String
    val routerState: IRouterState
}

class HomeState @Inject constructor(
    val dispatchers: DispatchersBin,
    override val routerState: IRouterState,
    val interactor: Interactor
) : IHomeState {
    override val name: String
        get() {
            return " HomeState"
        }
}