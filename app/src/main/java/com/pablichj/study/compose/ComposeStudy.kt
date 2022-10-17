package com.pablichj.study.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pablichj.study.compose.router.DrawerNavigationComponent
import com.pablichj.study.compose.router.RouterState

@Composable
fun ComposeStudy(
    modifier: Modifier = Modifier,
    composeStudyViewModel: IComposeStudyViewModel,
    routerState: RouterState,
    router: @Composable () -> Unit
) {

    val currentRoute = remember {
        composeStudyViewModel.routerState.routeFlow.value
    }
    SideEffect {
        Log.d("ComposeStudy", "Pablo currentRoute = $currentRoute")
    }

    DrawerNavigationComponent(
        modifier = modifier,
        onItemClick = { globalScreen ->
            routerState.navigate(globalScreen)
        },
        content = router
    )
}