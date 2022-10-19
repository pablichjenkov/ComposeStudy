package com.pablichj.study.compose.root

import androidx.lifecycle.ViewModel
import com.pablichj.study.compose.common.DispatchersBin
import com.pablichj.study.compose.data.Interactor
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.RouterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootStateStateViewModel @Inject constructor(
    val rootState: IRootState
) : ViewModel()

interface IRootState {
    val routerState: IRouterState
}

class RootState @Inject constructor(
    override val routerState: IRouterState,
    val interactor: Interactor,
    val dispatchersBin: DispatchersBin
) : IRootState {
}