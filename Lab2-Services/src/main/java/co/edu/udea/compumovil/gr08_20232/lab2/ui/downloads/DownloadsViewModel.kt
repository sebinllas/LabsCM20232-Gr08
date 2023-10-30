package co.edu.udea.compumovil.gr08_20232.lab2.ui.downloads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.gr08_20232.lab2.data.posts.DownloadedPostsRepository
import co.edu.udea.compumovil.gr08_20232.lab2.model.Post

class DownloadsViewModel(
    private val downloadsRepository: DownloadedPostsRepository
): ViewModel() {
    fun getDownloads(): List<Post> {
        return downloadsRepository.getPosts().getOrThrow()
    }


    private val _selectedPost = MutableLiveData<Post?>(null)
    val selectedPost: LiveData<Post?> = _selectedPost

    fun selectPost(selectedPost: Post?) {
        _selectedPost.value = selectedPost
    }


    companion object {
        fun provideFactory(
            downloadsRepository: DownloadedPostsRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DownloadsViewModel(downloadsRepository) as T
            }
        }
    }
}