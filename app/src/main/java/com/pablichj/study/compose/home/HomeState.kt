package com.pablichj.study.compose.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeState @Inject constructor() : IHomeState, ViewModel() {
    override val name: String
        get() {
            return " HomeState"
        }
}

interface IHomeState {
    val name: String
}