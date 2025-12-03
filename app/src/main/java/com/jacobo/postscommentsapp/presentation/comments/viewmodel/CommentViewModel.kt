package com.jacobo.postscommentsapp.presentation.comments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jacobo.postscommentsapp.domain.model.Comment
import com.jacobo.postscommentsapp.domain.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
) : ViewModel() {

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()

    fun loadCommentsByPost(postId: Int) {
        viewModelScope.launch {
            repository.getCommentsByPostId(postId)
                .collect { list ->
                    _comments.value = list
                }
        }
    }

    fun addComment(comment: Comment) {
        viewModelScope.launch {
            repository.insertComment(comment)
        }
    }
}