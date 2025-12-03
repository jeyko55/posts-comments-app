package com.jacobo.postscommentsapp.data.source.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jacobo.postscommentsapp.data.source.local.database.AppDatabase
import com.jacobo.postscommentsapp.data.source.local.entity.PostEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PostDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var postDao: PostDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        postDao = db.postDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAndGetAllPosts() = runBlocking {
        val post1 = PostEntity(id = 1, userId = 1, title = "Hello World", body = "Body1")
        val post2 = PostEntity(id = 2, userId = 1, title = "Compose Test", body = "Body2")

        postDao.insertPosts(listOf(post1, post2))

        val allPosts = postDao.getAllPosts().first()
        assertEquals(2, allPosts.size)
        assertEquals(post1.title, allPosts[0].title)
        assertEquals(post2.title, allPosts[1].title)
    }

    @Test
    fun getPostById() = runBlocking {
        val post = PostEntity(id = 1, userId = 1, title = "Hello", body = "Body")
        postDao.insertPosts(listOf(post))

        val retrieved = postDao.getPostById(1)
        assertEquals(post.title, retrieved?.title)
    }

    @Test
    fun searchPostsByTitle() = runBlocking {
        val post1 = PostEntity(id = 1, userId = 1, title = "Hello World", body = "Body1")
        val post2 = PostEntity(id = 2, userId = 1, title = "Compose Test", body = "Body2")
        postDao.insertPosts(listOf(post1, post2))

        val results = postDao.searchPostsByTitle("Hello").first()
        assertEquals(1, results.size)
        assertEquals("Hello World", results[0].title)
    }
}
