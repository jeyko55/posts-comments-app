package com.jacobo.postscommentsapp.domain.repository

import com.jacobo.postscommentsapp.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    fun getCommentsByPostId(postId: Int): Flow<List<Comment>>

    suspend fun insertComment(comment: Comment)
}
