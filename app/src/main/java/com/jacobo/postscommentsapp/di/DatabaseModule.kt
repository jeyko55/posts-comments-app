package com.jacobo.postscommentsapp.d

import android.content.Context
import androidx.room.Room
import com.jacobo.postscommentsapp.data.source.local.dao.CommentDao
import com.jacobo.postscommentsapp.data.source.local.dao.PostDao
import com.jacobo.postscommentsapp.data.source.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Módulo de Dagger Hilt para proveer dependencias
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides // Proporciona una instancia de la base de datos
    @Singleton // Singleton para que solo se cree una instancia de la base de datos
    fun provideDatabase(
        @ApplicationContext context: Context // Inyección de contexto
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "posts_comments_db"
        ).build()
    }

    @Provides
    fun providePostDao(database: AppDatabase): PostDao {
        return database.postDao()
    }

    @Provides
    fun provideCommentDao(database: AppDatabase): CommentDao {
        return database.commentDao()
    }
}
