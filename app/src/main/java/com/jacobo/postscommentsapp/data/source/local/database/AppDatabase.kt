package com.jacobo.postscommentsapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jacobo.postscommentsapp.data.source.local.dao.CommentDao
import com.jacobo.postscommentsapp.data.source.local.dao.PostDao
import com.jacobo.postscommentsapp.data.source.local.entity.CommentEntity
import com.jacobo.postscommentsapp.data.source.local.entity.PostEntity

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
