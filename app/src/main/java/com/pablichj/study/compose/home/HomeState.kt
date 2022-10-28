package com.pablichj.study.compose.home

import androidx.lifecycle.ViewModel
import com.pablichj.study.compose.common.DispatchersBin
import com.pablichj.study.compose.data.Interactor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeStateViewModel @Inject constructor(val homeState: IHomeState) : ViewModel()

interface IHomeState {
    val name: String
}

class HomeState @Inject constructor(
    val dispatchers: DispatchersBin,
    val interactor: Interactor
) : IHomeState {
    override val name: String
        get() {
            return " HomeState"
        }
}