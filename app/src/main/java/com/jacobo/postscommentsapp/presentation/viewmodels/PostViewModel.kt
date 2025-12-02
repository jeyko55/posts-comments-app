package com.jacobo.postscommentsapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jacobo.postscommentsapp.domain.model.Post
import com.jacobo.postscommentsapp.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    // Estado interno
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Cargar posts desde BD
    init {
        observePosts()
    }

    private fun observePosts() {
        viewModelScope.launch {
            repository.getPosts()
                .collect { postList ->
                    _posts.value = postList
                }
        }
    }

    // Sincronizar desde API
    fun fetchPostsFromApi() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.fetchAndStorePosts()
            _isLoading.value = false
        }
    }

    // Búsqueda por título
    fun searchPosts(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                observePosts()
            } else {
                repository.searchPostsByTitle(query)
                    .collect { results ->
                        _posts.value = results
                    }
            }
        }
    }
}
