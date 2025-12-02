package com.jacobo.postscommentsapp.domain.repository

import com.jacobo.postscommentsapp.domain.model.Comment
import com.jacobo.postscommentsapp.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    // POSTS

    // Obtener todos los posts desde BD (reactivo)
    fun getPosts(): Flow<List<Post>>

    // Buscar posts por título
    fun searchPostsByTitle(query: String): Flow<List<Post>>

    // Obtener un post por ID
    suspend fun getPostById(postId: Int): Post?

    // Sincronizar posts desde la API hacia Room
    suspend fun fetchAndStorePosts()


    // COMMENTS

    // Obtener comentarios de un post
    fun getCommentsByPost(postId: Int): Flow<List<Comment>>

    // Agregar un comentario a un post
    suspend fun addComment(comment: Comment)
}
