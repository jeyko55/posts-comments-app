package com.jacobo.postscommentsapp.data.local.entity

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
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["postId"])]
)
data class CommentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val postId: Int,

    val name: String,

    val email: String,

    val body: String
)
