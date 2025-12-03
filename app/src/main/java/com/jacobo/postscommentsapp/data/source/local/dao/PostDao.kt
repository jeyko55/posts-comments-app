package com.jacobo.postscommentsapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jacobo.postscommentsapp.data.source.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    // Guardar lista de posts desde la API
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    // Obtener todos los posts
    @Query("SELECT * FROM posts ORDER BY id ASC")
    fun getAllPosts(): Flow<List<PostEntity>>

    // Buscar por ID
    @Query("SELECT * FROM posts WHERE id = :postId")
    suspend fun getPostById(postId: Int): PostEntity?

    // Buscar por título
    @Query("SELECT * FROM posts WHERE title LIKE '%' || :query || '%' ORDER BY id ASC")
    fun searchPostsByTitle(query: String): Flow<List<PostEntity>>

}
