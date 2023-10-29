package com.example.jetnews.data.posts.impl

import com.example.jetnews.JetnewsApplication
import com.example.jetnews.data.posts.DownloadedPostsRepository
import com.example.jetnews.model.Post
import com.google.gson.Gson

class DownloadedPostsRepositoryImpl: DownloadedPostsRepository {
    override fun getPosts(): Result<List<Post>> {

        val gson = Gson()

        val posts: MutableList<Post> = emptyList<Post>().toMutableList()

        val context = JetnewsApplication.applicationContext()
        val appDir = context.getExternalFilesDir(null)
        val files = appDir?.listFiles()

        if (files != null) {
            for(file in files){
                val post = gson.fromJson(file.readText(), Post::class.java)
                posts.add(post)
            }
        }

        return Result.success(posts)

    }
}