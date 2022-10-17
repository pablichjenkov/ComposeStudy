package com.pablichj.study.compose.home

import com.pablichj.study.compose.common.DispatchersBin
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun providesDispatcherContainers(): DispatchersBin {
        return DispatchersBin()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModuleAdapter {

    @Binds
    abstract fun bindHomeState(
        homeState: HomeState
    ): IHomeState

}