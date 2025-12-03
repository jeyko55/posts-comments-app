package com.jacobo.postscommentsapp.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jacobo.postscommentsapp.domain.model.Post
import com.jacobo.postscommentsapp.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    // Estado
    private val _query = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Posts reactivos con búsqueda (debounce + flatMapLatest)
    val posts: StateFlow<List<Post>> = _query
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.getPosts()
            } else {
                repository.searchPostsByTitle(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    // Sincronización desde API
    fun fetchPostsFromApi() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.fetchAndStorePosts()
            _isLoading.value = false
        }
    }

    // Actualizar búsqueda (la UI debe llamar a esto)
    fun updateSearchQuery(query: String) {
        _query.value = query
    }

    // Nota: no hay método searchPosts() imperativo porque la búsqueda es reactiva.
}