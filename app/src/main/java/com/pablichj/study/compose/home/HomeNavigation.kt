/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pablichj.study.compose.home

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablichj.study.compose.common.Screen

internal fun NavGraphBuilder.homeGraph(
    onTopButtonClick: () -> Unit
) {
    composable(Screen.Home.route) { backStackEntry ->
        val homeState = hiltViewModel<HomeState>(backStackEntry)
        Home(homeState = homeState, onTopButtonClick = onTopButtonClick)
    }
}