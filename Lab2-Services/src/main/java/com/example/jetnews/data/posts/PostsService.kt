package com.example.jetnews.data.posts

import com.example.jetnews.model.Post
import com.example.jetnews.model.PostsFeed
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostsService {
    companion object {
        private const val BASE_URL = "http://192.168.1.21:3000/"
        val instanace: PostsService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PostsService::class.java)
    }

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("feed")
    suspend fun getPostsFeed(): PostsFeed

    @GET("posts/{postId}")
    suspend fun getPost(postId: String): Post

}