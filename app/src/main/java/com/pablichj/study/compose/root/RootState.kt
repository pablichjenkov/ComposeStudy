package com.pablichj.study.compose.root

import androidx.lifecycle.ViewModel
import com.pablichj.study.compose.common.DispatchersBin
import com.pablichj.study.compose.data.Interactor
import com.pablichj.study.compose.router.IRouterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootStateStateViewModel @Inject constructor(
    val rootState: IRootState,
    val routerState: IRouterState
) : ViewModel()

interface IRootState {
    val rootNavActions: RootNavActions
}

class RootState @Inject constructor(
    override val rootNavActions: RootNavActions,
    val interactor: Interactor,
    val dispatchersBin: DispatchersBin
) : IRootState {
}