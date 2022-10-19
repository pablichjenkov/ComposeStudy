package com.pablichj.study.compose.common

object Platform {
    enum class System {
        ANDROID,
        IOS,
        MACOS,
        WINDOWS
    }

    val system: System = System.ANDROID
}