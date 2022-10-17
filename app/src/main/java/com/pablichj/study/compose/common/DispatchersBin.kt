package com.pablichj.study.compose.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchersBin(
    val main: CoroutineDispatcher = Dispatchers.Main,
    val io: CoroutineDispatcher = Dispatchers.IO,
    val default: CoroutineDispatcher = Dispatchers.Default,
    val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
)