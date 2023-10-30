package co.edu.udea.compumovil.gr08_20232.lab2.data.posts.impl

import co.edu.udea.compumovil.gr08_20232.lab2.JetnewsApplication
import co.edu.udea.compumovil.gr08_20232.lab2.data.posts.DownloadedPostsRepository
import co.edu.udea.compumovil.gr08_20232.lab2.model.Post
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