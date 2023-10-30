package co.edu.udea.compumovil.gr08_20232.lab2.data.posts

import co.edu.udea.compumovil.gr08_20232.lab2.model.Post
import co.edu.udea.compumovil.gr08_20232.lab2.model.PostsFeed
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostsService {
    companion object {
        private const val BASE_URL = "https://jetnews-api.onrender.com/"
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