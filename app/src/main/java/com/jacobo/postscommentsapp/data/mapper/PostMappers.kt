package com.jacobo.postscommentsapp.data.mapper

import com.jacobo.postscommentsapp.data.source.local.entity.PostEntity
import com.jacobo.postscommentsapp.data.source.remote.dto.PostDto
import com.jacobo.postscommentsapp.domain.model.Post

// DTO -> Entity (API -> Room)
fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        title = title,
        body = body,
        userId = userId
    )
}

// Entity -> Domain (Room -> UI)
fun PostEntity.toDomain(): Post {
    return Post(
        id = id,
        title = title,
        body = body,
        userId = userId
    )
}
