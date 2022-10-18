package com.pablichj.study.compose

import androidx.lifecycle.ViewModel
import com.pablichj.study.compose.data.Interactor
import com.pablichj.study.compose.router.RouterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComposeStudyStateViewModel @Inject constructor(
    val composeStudyState: IComposeStudyState
) : ViewModel()

interface IComposeStudyState {
    val routerState: RouterState
}

class ComposeStudyState @Inject constructor(
    val interactor: Interactor
) : IComposeStudyState {

    val router = RouterState()
    override val routerState: RouterState
        get() = router
}