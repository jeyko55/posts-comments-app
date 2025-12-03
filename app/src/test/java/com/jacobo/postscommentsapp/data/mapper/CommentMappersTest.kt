package com.jacobo.postscommentsapp.data.mapper

import com.jacobo.postscommentsapp.data.source.local.entity.CommentEntity
import com.jacobo.postscommentsapp.domain.model.Comment
import org.junit.Assert.assertEquals
import org.junit.Test

class CommentMappersTest {

    @Test
    fun `commentEntity toDomain maps correctly`() { // Mapeo de CommentEntity a Comment
        // Given
        val entity = CommentEntity(
            id = 5,
            postId = 10,
            name = "John Doe",
            body = "Great post!"
        )

        // When
        val domain = entity.toDomain()

        // Then
        val expected = Comment(
            id = 5,
            postId = 10,
            name = "John Doe",
            body = "Great post!"
        )
        assertEquals(expected, domain)
    }

    @Test
    fun `comment toEntity maps correctly`() {
        // Given
        val comment = Comment(
            id = 5,
            postId = 10,
            name = "Jane Smith",
            body = "Nice article"
        )

        // When
        val entity = comment.toEntity()

        // Then
        val expected = CommentEntity(
            id = 5,
            postId = 10,
            name = "Jane Smith",
            body = "Nice article"
        )
        assertEquals(expected, entity)
    }

    @Test
    fun `comment with auto-generated id maps correctly`() {
        // Given
        val comment = Comment(
            id = 0, // Auto-generated
            postId = 10,
            name = "Test User",
            body = "Test Comment"
        )

        // When
        val entity = comment.toEntity()

        // Then
        val expected = CommentEntity(
            id = 0,
            postId = 10,
            name = "Test User",
            body = "Test Comment"
        )
        assertEquals(expected, entity)
    }

    @Test
    fun `multiple comments conversion maps all fields correctly`() {
        // Given
        val comments = listOf(
            Comment(1, 10, "Alice", "First comment"),
            Comment(2, 10, "Bob", "Second comment"),
            Comment(3, 11, "Charlie", "Third comment")
        )

        // When
        val entities = comments.map { it.toEntity() }

        // Then
        val expected = listOf(
            CommentEntity(1, 10, "Alice", "First comment"),
            CommentEntity(2, 10, "Bob", "Second comment"),
            CommentEntity(3, 11, "Charlie", "Third comment")
        )
        assertEquals(expected, entities)
    }

    @Test
    fun `comment toEntity handles empty strings correctly`() {
        // Given
        val comment = Comment(
            id = 1,
            postId = 5,
            name = "",
            body = ""
        )

        // When
        val entity = comment.toEntity()

        // Then
        val expected = CommentEntity(
            id = 1,
            postId = 5,
            name = "",
            body = ""
        )
        assertEquals(expected, entity)
    }
}
