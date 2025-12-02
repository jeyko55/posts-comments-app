package com.jacobo.postscommentsapp.data.repository

import com.jacobo.postscommentsapp.data.source.local.dao.PostDao
import com.jacobo.postscommentsapp.data.mapper.toDomain
import com.jacobo.postscommentsapp.data.mapper.toEntity
import com.jacobo.postscommentsapp.data.source.remote.api.ApiService
import com.jacobo.postscommentsapp.domain.model.Post
import com.jacobo.postscommentsapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao,
) : PostRepository {

    override fun getPosts(): Flow<List<Post>> {
        return postDao.getAllPosts()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override fun searchPostsByTitle(query: String): Flow<List<Post>> {
        return postDao.searchPostsByTitle(query)
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override suspend fun getPostById(postId: Int): Post? {
        return postDao.getPostById(postId)?.toDomain()
    }

    override suspend fun fetchAndStorePosts() {
        val postsFromApi = apiService.getPosts()
        val entities = postsFromApi.map { it.toEntity() }
        postDao.insertPosts(entities)
    }
}
