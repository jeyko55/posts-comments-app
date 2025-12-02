package com.jacobo.postscommentsapp.data.source.remote.api

import com.jacobo.postscommentsapp.data.source.remote.dto.PostDto
import retrofit2.http.GET

interface ApiService { // Retrofit implementa esta interfaz

    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}
