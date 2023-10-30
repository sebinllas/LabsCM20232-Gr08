package co.edu.udea.compumovil.gr08_20232.lab2.data.posts.impl

import android.util.Log
import co.edu.udea.compumovil.gr08_20232.lab2.data.Result
import co.edu.udea.compumovil.gr08_20232.lab2.data.posts.PostsRepository
import co.edu.udea.compumovil.gr08_20232.lab2.data.posts.PostsService
import co.edu.udea.compumovil.gr08_20232.lab2.model.Post
import co.edu.udea.compumovil.gr08_20232.lab2.model.PostsFeed
import co.edu.udea.compumovil.gr08_20232.lab2.utils.addOrRemove
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ApiPostsRepository(//constructor
    private val postsService: PostsService
): PostsRepository {

    private val favorites = MutableStateFlow<Set<String>>(setOf())

    private val postsFeed = MutableStateFlow<PostsFeed?>(null)

    override suspend fun getPost(postId: String?): Result<Post> {
        return Result.Success(postsService.getPost(postId!!))
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        val feed = postsService.getPostsFeed()
        Log.d("feed-debug", feed.toString())
        return Result.Success(feed)
    }

    override fun observeFavorites(): Flow<Set<String>> = favorites
    override fun observePostsFeed(): Flow<PostsFeed?> = postsFeed

    override suspend fun toggleFavorite(postId: String) {
        favorites.update { it.addOrRemove(postId) }
    }

}