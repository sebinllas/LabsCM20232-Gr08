package com.example.jetnews.ui.downloads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetnews.data.interests.InterestsRepository
import com.example.jetnews.data.posts.DownloadedPostsRepository
import com.example.jetnews.model.Post
import com.example.jetnews.ui.interests.InterestsViewModel

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