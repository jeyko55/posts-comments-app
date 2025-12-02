package com.jacobo.postscommentsapp.data.remote.dto

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)