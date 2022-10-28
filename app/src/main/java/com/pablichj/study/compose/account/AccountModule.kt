package com.pablichj.study.compose.account

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModuleAdapter {

    @Binds
    abstract fun bindHomeViewModel(
        homeState: AccountState
    ): IAccountState

}