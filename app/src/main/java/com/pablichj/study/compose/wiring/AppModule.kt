package com.pablichj.study.compose.wiring

import com.pablichj.study.compose.common.DispatchersBin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDispatcherContainers(): DispatchersBin {
        return DispatchersBin()
    }

}