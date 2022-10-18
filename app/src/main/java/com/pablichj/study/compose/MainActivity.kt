package com.pablichj.study.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pablichj.study.compose.router.Router
import com.pablichj.study.compose.ui.theme.ComposeStudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val composeStudyStateViewModel by viewModels<ComposeStudyStateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val composeStudyState = composeStudyStateViewModel.composeStudyState

                    /** A Router and a RouterState are intended to be provided per each OS platform.
                     * Android is based on NavHost and NavController but Desktop could be using
                     * something different.
                     * */
                    ComposeStudy(
                        composeStudyViewModel = composeStudyState,
                        routerState = composeStudyState.routerState,
                    ) {
                        Router(routerState = composeStudyState.routerState)
                    }
                }
            }
        }
    }
}