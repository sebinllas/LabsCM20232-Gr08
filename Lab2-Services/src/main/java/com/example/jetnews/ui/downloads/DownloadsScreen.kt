import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.jetnews.R
import com.example.jetnews.model.Post
import com.example.jetnews.ui.article.ArticleScreen
import com.example.jetnews.ui.home.PostCardSimple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadsScreen(
    downloadedPosts: List<Post>,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState,
    selectedPost: Post? = null,
    onSelectPost: (Post?) -> Unit,
) {
    val context = LocalContext.current
    fun handleToggleFavorite() {
        Toast.makeText(
            context,
            context.getString(R.string.favorites_not_available_message),
            Toast.LENGTH_LONG
        ).show()
    }
    if (selectedPost != null) {
        ArticleScreen(
            post = selectedPost,
            isExpandedScreen = false,
            onBack = { onSelectPost(null) },
            isFavorite = false,
            onToggleFavorite = { handleToggleFavorite() },
        )
    } else {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.cd_downloads),
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    navigationIcon = {
                        if (!isExpandedScreen) {
                            IconButton(onClick = openDrawer) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_jetnews_logo),
                                    contentDescription = stringResource(
                                        R.string.cd_open_navigation_drawer
                                    ),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Search is not yet implemented in this configuration",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(R.string.cd_search)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            val screenModifier = Modifier.padding(innerPadding)
            Column(
                modifier = screenModifier
            ) {

                downloadedPosts.forEach { post ->
                    PostCardSimple(post = post, navigateToArticle = {
                        onSelectPost(post)
                    }, isFavorite = false) {
                        handleToggleFavorite()
                    }

                }

            }
        }
    }

}



