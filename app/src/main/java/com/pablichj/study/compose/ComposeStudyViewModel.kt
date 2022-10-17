package com.pablichj.study.compose

import androidx.lifecycle.ViewModel
import com.pablichj.study.compose.router.RouterState
import javax.inject.Inject

interface IComposeStudyViewModel {
    val routerState:  RouterState
}

class ComposeStudyViewModel @Inject constructor() : IComposeStudyViewModel, ViewModel() {

    val router = RouterState()
    override val routerState: RouterState
        get() = router
}