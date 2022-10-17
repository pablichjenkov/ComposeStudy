package com.pablichj.study.compose.common

/*internal */sealed class Screen(val route: String) {

    fun createRoute(root: Screen) = "${root.route}/$route"
    
    object Home : Screen("home")
    object Account : Screen("account")
    object Orders : Screen("orders")
    
}