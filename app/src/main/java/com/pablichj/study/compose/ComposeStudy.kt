package com.pablichj.study.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pablichj.study.compose.router.DrawerNavigationComponent
import com.pablichj.study.compose.router.RouterState

@Composable
fun ComposeStudy(
    modifier: Modifier = Modifier,
    composeStudyViewModel: IComposeStudyState,
    routerState: RouterState,
    router: @Composable () -> Unit
) {

    val currentRoute = remember {
        composeStudyViewModel.routerState.currentRoute
    }

    Log.d("ComposeStudy", "Pablo currentRoute = $currentRoute")

    DrawerNavigationComponent(
        modifier = modifier,
        onItemClick = { globalScreen ->
            routerState.navigate(globalScreen)
        },
        content = router
    )
}