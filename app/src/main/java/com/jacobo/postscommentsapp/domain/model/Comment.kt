package com.jacobo.postscommentsapp.domain.model

data class Comment(
    val id: Int = 0, // Igualmente Room autogenera el id
    val postId: Int,
    val name: String,
    val body: String
)
