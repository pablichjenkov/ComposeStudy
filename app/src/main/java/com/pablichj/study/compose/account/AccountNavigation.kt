package com.pablichj.study.compose.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablichj.study.compose.router.Node

object AccountNode : Node("account")

internal fun NavGraphBuilder.accountGraph() {

    composable(AccountNode.route) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Account Screen")
        }
    }

}