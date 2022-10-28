package com.pablichj.study.compose.account

import androidx.lifecycle.ViewModel
import com.pablichj.study.compose.common.DispatchersBin
import com.pablichj.study.compose.data.Interactor
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.Node
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountStateViewModel @Inject constructor(val accountState: IAccountState) : ViewModel()

interface IAccountState {
    val accountName: String
    val routerState: IRouterState
}

class AccountState @Inject constructor(
    val dispatchers: DispatchersBin,
    override val routerState: IRouterState,
    val interactor: Interactor
) : IAccountState {
    override val accountName: String
        get() {
            return " User: Pablo"
        }
}