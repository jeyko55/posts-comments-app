package com.jacobo.postscommentsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jacobo.postscommentsapp.data.local.dao.CommentDao
import com.jacobo.postscommentsapp.data.local.dao.PostDao
import com.jacobo.postscommentsapp.data.local.entity.CommentEntity
import com.jacobo.postscommentsapp.data.local.entity.PostEntity

@Database(
    entities = [
        PostEntity::class,
        CommentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    abstract fun commentDao(): CommentDao
}
