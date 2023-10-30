/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetnews.ui.downloads

import DownloadsScreen
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.jetnews.model.Post
import com.example.jetnews.ui.home.HomeViewModel

/**
 * Stateful composable that displays the Navigation route for the Interests screen.
 *
 * @param interestsViewModel ViewModel that handles the business logic of this screen
 * @param isExpandedScreen (state) true if the screen is expanded
 * @param openDrawer (event) request opening the app drawer
 * @param snackbarHostState (state) state for screen snackbar host
 */
@Composable
fun DownloadsRoute(
    downloadsViewModel: DownloadsViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {

    val downloadedPosts = downloadsViewModel.getDownloads()
    val selectedPost by downloadsViewModel.selectedPost.observeAsState(null)

    DownloadsScreen(
        downloadedPosts = downloadedPosts,
        isExpandedScreen = isExpandedScreen,
        openDrawer = openDrawer,
        snackbarHostState = snackbarHostState,
        onSelectPost = { post: Post? ->
            downloadsViewModel.selectPost(post)
        },
        selectedPost = selectedPost
    )
}