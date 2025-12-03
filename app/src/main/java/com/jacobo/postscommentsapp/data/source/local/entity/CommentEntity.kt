package com.jacobo.postscommentsapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = PostEntity::class,
            parentColumns = ["id"],
            childColumns = ["postId"],
            onDelete = ForeignKey.NO_ACTION // No se eliminará el post cada que se haga GET a la API
        )
    ],
    indices = [Index(value = ["postId"])]
)
data class CommentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val postId: Int,

    val name: String,

    val body: String
)
