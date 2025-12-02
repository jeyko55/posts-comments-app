package com.jacobo.postscommentsapp.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class PostDto(

    @SerializedName("userId") // Serialización con Gson
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String
)
