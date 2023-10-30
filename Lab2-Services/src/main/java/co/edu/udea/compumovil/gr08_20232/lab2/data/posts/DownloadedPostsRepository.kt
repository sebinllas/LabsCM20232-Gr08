package co.edu.udea.compumovil.gr08_20232.lab2.data.posts

import co.edu.udea.compumovil.gr08_20232.lab2.model.Post

interface DownloadedPostsRepository {
    fun getPosts(): Result<List<Post>>
}