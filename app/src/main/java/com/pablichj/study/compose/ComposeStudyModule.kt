package com.pablichj.study.compose

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ComposeStudyModule {
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ComposeStudyModuleAdapter {

    @Binds
    abstract fun bindComposeStudyViewModel(
        composeStudyViewModel: ComposeStudyState
    ): IComposeStudyState

}