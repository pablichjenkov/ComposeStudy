package com.pablichj.study.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.pablichj.study.compose.router.Router
import com.pablichj.study.compose.ui.theme.ComposeStudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //@Inject
    //lateinit var composeStudyViewModel: ComposeStudyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val composeStudyViewModel = ViewModelProvider(this)[ComposeStudyViewModel::class.java]

        setContent {
            ComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeStudy(
                        composeStudyViewModel = composeStudyViewModel,
                        routerState = composeStudyViewModel.routerState,
                    ) {
                        Router(routerState = composeStudyViewModel.routerState)
                    }
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStudyTheme {

        // TODO(Pablo): Place the PreviewComposeStudyState class in a debug variant only
        val previewComposeStudyState = object : IComposeStudyState {
            override val homeState: IHomeState
                get() = HomeState()
            override val orderState: String
                get() = "orderState"
            override val accountState: String
                get() = "accountState"
        }

        ComposeStudy(Modifier.fillMaxSize(), previewComposeStudyState)
    }
}*/
