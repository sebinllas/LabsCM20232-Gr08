package com.example.jetnews.data.posts

import com.example.jetnews.model.Post

interface DownloadedPostsRepository {
    fun getPosts(): Result<List<Post>>
}