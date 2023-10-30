/*
 * Copyright 2020 The Android Open Source Project
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

package co.edu.udea.compumovil.gr08_20232.lab2.ui.article

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr08_20232.lab2.FileSaveService
import com.example.jetnews.R
import co.edu.udea.compumovil.gr08_20232.lab2.data.Result
import co.edu.udea.compumovil.gr08_20232.lab2.data.posts.impl.BlockingFakePostsRepository
import co.edu.udea.compumovil.gr08_20232.lab2.data.posts.impl.post3
import co.edu.udea.compumovil.gr08_20232.lab2.model.Post
import co.edu.udea.compumovil.gr08_20232.lab2.ui.theme.JetnewsTheme
import co.edu.udea.compumovil.gr08_20232.lab2.ui.utils.BookmarkButton
import co.edu.udea.compumovil.gr08_20232.lab2.ui.utils.DownloadButton
import co.edu.udea.compumovil.gr08_20232.lab2.ui.utils.FavoriteButton
import co.edu.udea.compumovil.gr08_20232.lab2.ui.utils.ShareButton
import co.edu.udea.compumovil.gr08_20232.lab2.ui.utils.TextSettingsButton
import kotlinx.coroutines.runBlocking

/**
 * Stateless Article Screen that displays a single post adapting the UI to different screen sizes.
 *
 * @param post (state) item to display
 * @param showNavigationIcon (state) if the navigation icon should be shown
 * @param onBack (event) request navigate back
 * @param isFavorite (state) is this item currently a favorite
 * @param onToggleFavorite (event) request that this post toggle it's favorite state
 * @param lazyListState (state) the [LazyListState] for the article content
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    post: Post,
    isExpandedScreen: Boolean,
    onBack: () -> Unit,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState()
) {
    var showUnimplementedActionDialog by rememberSaveable { mutableStateOf(false) }
    var showChangeFontDialog by rememberSaveable { mutableStateOf(false) }
    var fontScaleFactor by rememberSaveable { mutableFloatStateOf(1.0f) }
    var context = LocalContext.current;
    if (showUnimplementedActionDialog) {
        FunctionalityNotAvailablePopup { showUnimplementedActionDialog = false }
    }
    if (showChangeFontDialog) {
        ChangeFontPopUp(
            onDismiss = { showChangeFontDialog = false },
            value = fontScaleFactor.toFloat(),
            onValueChange = { fontScaleFactor = it })
    }

    Row(modifier.fillMaxSize()) {
        val context = LocalContext.current
        ArticleScreenContent(
            post = post,
            fontScaleFactor = fontScaleFactor,
            // Allow opening the Drawer if the screen is not expanded
            navigationIconContent = {
                if (!isExpandedScreen) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_navigate_up),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            // Show the bottom bar if the screen is not expanded
            bottomBarContent = {
                if (!isExpandedScreen) {
                    BottomAppBar(
                        actions = {
                            FavoriteButton(onClick = { showUnimplementedActionDialog = true })
                            BookmarkButton(isBookmarked = isFavorite, onClick = onToggleFavorite)
                            ShareButton(onClick = { sharePost(post, context) })
                            TextSettingsButton(onClick = { showChangeFontDialog = true })
                            DownloadButton {
                                val intent = Intent(context, FileSaveService::class.java)
                                intent.putExtra("file_name", post.title)
                                intent.putExtra("file_content", post.toJson())
                                context.startService(intent)

                            }
                        }
                    )
                }
            },
            lazyListState = lazyListState
        )
    }
}

/**
 * Stateless Article Screen that displays a single post.
 *
 * @param post (state) item to display
 * @param navigationIconContent (UI) content to show for the navigation icon
 * @param bottomBarContent (UI) content to show for the bottom bar
 */
@ExperimentalMaterial3Api
@Composable
private fun ArticleScreenContent(
    post: Post,
    navigationIconContent: @Composable () -> Unit = { },
    bottomBarContent: @Composable () -> Unit = { },
    lazyListState: LazyListState = rememberLazyListState(),
    fontScaleFactor: Float = 1f
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    Scaffold(
        topBar = {
            TopAppBar(
                title = post.publication?.name.orEmpty(),
                navigationIconContent = navigationIconContent,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = bottomBarContent
    ) { innerPadding ->
        PostContent(
            post = post,
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                // innerPadding takes into account the top and bottom bar
                .padding(innerPadding),
            state = lazyListState,
            fontScaleFactor = fontScaleFactor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    title: String,
    navigationIconContent: @Composable () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior?,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.icon_article_background),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(36.dp)
                )
                Text(
                    text = stringResource(R.string.published_in, title),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        navigationIcon = navigationIconContent,
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

/**
 * Display a popup explaining functionality not available.
 *
 * @param onDismiss (event) request the popup be dismissed
 */
@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                text = stringResource(id = R.string.article_functionality_not_available),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.close))
            }
        }
    )
}

@Composable
private fun ChangeFontPopUp(onDismiss: () -> Unit, value: Float, onValueChange: (Float) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Column {
                Text(
                    stringResource(id = R.string.cd_text_settings),
                    style = MaterialTheme.typography.bodyLarge.merge(
                        TextStyle(
                            fontSize = TextUnit(
                                value * MaterialTheme.typography.bodyLarge.fontSize.value,
                                TextUnitType.Sp
                            )
                        )
                    ),
                )
                Slider(
                    value = value,
                    onValueChange = onValueChange,
                    valueRange = 0.8f..1.4f,
                    steps = 2
                )
                Text(
                    when (value) {
                        0.8f -> stringResource(id = R.string.small)
                        1.0f -> stringResource(id = R.string.medium)
                        1.2f -> stringResource(id = R.string.large)
                        1.4f -> stringResource(id = R.string.extra_large)
                        else -> stringResource(id = R.string.medium)
                    },
                    style = MaterialTheme.typography.bodyLarge.merge(
                        TextStyle(
                            fontSize = TextUnit(
                                value * MaterialTheme.typography.bodyLarge.fontSize.value,
                                TextUnitType.Sp
                            )
                        )
                    ),
                )

            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.close))
            }
        }
    )
}

/**
 * Show a share sheet for a post
 *
 * @param post to share
 * @param context Android context to show the share sheet in
 */
fun sharePost(post: Post, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, post.title)
        putExtra(Intent.EXTRA_TEXT, post.url)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.article_share_post)
        )
    )
}

@Preview("Article screen")
@Preview("Article screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("Article screen (big font)", fontScale = 1.5f)
@Composable
fun PreviewArticleDrawer() {
    JetnewsTheme {
        val post = runBlocking {
            (BlockingFakePostsRepository().getPost(post3.id) as Result.Success).data
        }
        ArticleScreen(post, false, {}, false, {})
    }
}

@Preview("Article screen navrail", device = Devices.PIXEL_C)
@Preview(
    "Article screen navrail (dark)",
    uiMode = UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_C
)
@Preview("Article screen navrail (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewArticleNavRail() {
    JetnewsTheme {
        val post = runBlocking {
            (BlockingFakePostsRepository().getPost(post3.id) as Result.Success).data
        }
        ArticleScreen(post, true, {}, false, {})
    }
}
