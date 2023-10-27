package com.example.jetnews.data.posts.impl

import android.util.Log
import com.example.jetnews.data.Result
import com.example.jetnews.data.posts.PostsRepository
import com.example.jetnews.data.posts.PostsService
import com.example.jetnews.model.Post
import com.example.jetnews.model.PostsFeed
import com.example.jetnews.utils.addOrRemove
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ApiPostsRepository(//constructor
    private val postsService: PostsService
): PostsRepository{

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