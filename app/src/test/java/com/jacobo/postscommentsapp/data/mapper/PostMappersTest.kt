package com.jacobo.postscommentsapp.data.mapper

import com.jacobo.postscommentsapp.data.source.local.entity.PostEntity
import com.jacobo.postscommentsapp.data.source.remote.dto.PostDto
import com.jacobo.postscommentsapp.domain.model.Post
import org.junit.Assert.assertEquals
import org.junit.Test

class PostMappersTest {

    @Test
    fun `postDto toEntity maps correctly`() { // Mapeo de PostDto a PostEntity
        // Given
        val dto = PostDto(
            userId = 1,
            id = 10,
            title = "Test Title",
            body = "Test Body"
        )

        // When
        val entity = dto.toEntity()

        // Then
        val expected = PostEntity(
            id = 10,
            userId = 1,
            title = "Test Title",
            body = "Test Body"
        )
        assertEquals(expected, entity)
    }

    @Test
    fun `postEntity toDomain maps correctly`() {
        // Given
        val entity = PostEntity(
            id = 10,
            userId = 1,
            title = "Test Title",
            body = "Test Body"
        )

        // When
        val domain = entity.toDomain()

        // Then
        val expected = Post(
            id = 10,
            userId = 1,
            title = "Test Title",
            body = "Test Body"
        )
        assertEquals(expected, domain)
    }

    @Test
    fun `multiple postDto to entity conversion maps all fields correctly`() {
        // Given
        val dtos = listOf(
            PostDto(1, 1, "Title 1", "Body 1"),
            PostDto(1, 2, "Title 2", "Body 2"),
            PostDto(2, 3, "Title 3", "Body 3")
        )

        // When
        val entities = dtos.map { it.toEntity() }

        // Then
        val expected = listOf(
            PostEntity(1, 1, "Title 1", "Body 1"),
            PostEntity(2, 1, "Title 2", "Body 2"),
            PostEntity(3, 2, "Title 3", "Body 3")
        )
        assertEquals(expected, entities)
    }

    @Test
    fun `postDto toEntity handles null or empty fields`() {
        // Given
        val dto = PostDto(
            userId = 0,
            id = 0,
            title = "",
            body = ""
        )

        // When
        val entity = dto.toEntity()

        // Then
        val expected = PostEntity(
            id = 0,
            userId = 0,
            title = "",
            body = ""
        )
        assertEquals(expected, entity)
    }
}
