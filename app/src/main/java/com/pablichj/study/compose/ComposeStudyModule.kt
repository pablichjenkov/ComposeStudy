package com.pablichj.study.compose

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ComposeStudyModule {
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ComposeStudyModuleAdapter {

    @Binds
    abstract fun bindHomeState(
        composeStudyViewModel: ComposeStudyViewModel
    ): IComposeStudyViewModel

}