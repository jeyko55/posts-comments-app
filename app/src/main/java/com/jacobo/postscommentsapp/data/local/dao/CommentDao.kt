package com.jacobo.postscommentsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jacobo.postscommentsapp.data.local.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    // Insertar un comentario
    @Insert(onConflict = OnConflictStrategy.REPLACE) // Si hay un conflicto, reemplazarlo
    suspend fun insertComment(comment: CommentEntity)

    // Insertar N comentarios
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentEntity>)

    // Obtener comentarios por post
    @Query("SELECT * FROM comments WHERE postId = :postId ORDER BY id DESC")
    fun getCommentsByPostId(postId: Int): Flow<List<CommentEntity>>
}
