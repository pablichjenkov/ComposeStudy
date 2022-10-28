package com.pablichj.study.compose.root

import com.pablichj.study.compose.common.DispatchersBin
import com.pablichj.study.compose.common.Platform
import com.pablichj.study.compose.router.IRouterState
import com.pablichj.study.compose.router.RouterState
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * A RootComposable lives in the Activity scope so lets install this module in the
 * ActivityRetainedComponent.
 * */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RootModule {

    @Provides
    @ActivityRetainedScoped
    fun providesRouter(
        dispatchersBin: DispatchersBin
    ): IRouterState {
        return when (Platform.system) {
            Platform.System.ANDROID -> RouterState(dispatchersBin)
            Platform.System.IOS,
            Platform.System.MACOS,
            Platform.System.WINDOWS -> RouterState(dispatchersBin)
        }
    }

}

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RootModuleAdapter {

    @Binds
    abstract fun bindRootState(
        rootState: RootState
    ): IRootState

}