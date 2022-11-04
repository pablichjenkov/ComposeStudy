package com.pablichj.study.compose.root.drawer

import androidx.compose.ui.graphics.vector.ImageVector
import com.pablichj.study.compose.root.RootNode

data class NavItemInfo(
    val label: String,
    val icon: ImageVector,
    val rootNode: RootNode,
    var selected: Boolean
)

