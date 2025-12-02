package com.jacobo.postscommentsapp.data.mapper

import com.jacobo.postscommentsapp.data.source.local.entity.CommentEntity
import com.jacobo.postscommentsapp.domain.model.Comment

// Entity -> Domain (Room -> UI)
fun CommentEntity.toDomain(): Comment {
    return Comment(
        id = id,
        postId = postId,
        name = name,
        email = email,
        body = body
    )
}

// Domain -> Entity (UI -> Room)
fun Comment.toEntity(): CommentEntity {
    return CommentEntity(
        id = id,
        postId = postId,
        name = name,
        email = email,
        body = body
    )
}
