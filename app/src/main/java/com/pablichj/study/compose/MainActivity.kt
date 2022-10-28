package com.pablichj.study.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.rememberNavController
import com.pablichj.study.compose.root.NavigationDrawerRoot
import com.pablichj.study.compose.root.RootStateStateViewModel
import com.pablichj.study.compose.root.rootGraph
import com.pablichj.study.compose.router.Router
import com.pablichj.study.compose.ui.theme.ComposeStudyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val rootStateStateViewModel by viewModels<RootStateStateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val rootState = rootStateStateViewModel.rootState

                    /** A Router is intended to be provided per each OS platform.
                     * Android is based on NavHost and NavController but Desktop could be using
                     * something different.
                     * */
                    NavigationDrawerRoot(rootState = rootState) {
                        Router(
                            navController = rememberNavController(),
                            coroutineScope = rememberCoroutineScope(),
                            routerState = rootState.routerState,
                            rootGraphBuilder = NavGraphBuilder::rootGraph
                        )
                    }
                }
            }
        }
    }
}