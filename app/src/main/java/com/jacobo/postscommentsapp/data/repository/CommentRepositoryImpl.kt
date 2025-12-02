package com.jacobo.postscommentsapp.data.repository

import com.jacobo.postscommentsapp.data.source.local.dao.CommentDao
import com.jacobo.postscommentsapp.data.mapper.toDomain
import com.jacobo.postscommentsapp.data.mapper.toEntity
import com.jacobo.postscommentsapp.domain.model.Comment
import com.jacobo.postscommentsapp.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val dao: CommentDao
) : CommentRepository {

    override fun getCommentsByPostId(postId: Int): Flow<List<Comment>> {
        return dao.getCommentsByPostId(postId)
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun insertComment(comment: Comment) {
        dao.insertComment(comment.toEntity())
    }
}
