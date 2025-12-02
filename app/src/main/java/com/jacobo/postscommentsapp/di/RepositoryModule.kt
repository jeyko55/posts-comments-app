package com.jacobo.postscommentsapp.di

import com.jacobo.postscommentsapp.data.repository.CommentRepositoryImpl
import com.jacobo.postscommentsapp.data.repository.PostRepositoryImpl
import com.jacobo.postscommentsapp.domain.repository.CommentRepository
import com.jacobo.postscommentsapp.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        impl: PostRepositoryImpl
    ): PostRepository

    @Binds
    @Singleton
    abstract fun bindCommentRepository(
        impl: CommentRepositoryImpl
    ): CommentRepository
}
