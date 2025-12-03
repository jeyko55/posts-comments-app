package com.jacobo.postscommentsapp.presentation.comments.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jacobo.postscommentsapp.domain.model.Comment
import com.jacobo.postscommentsapp.presentation.components.CommentItem
import com.jacobo.postscommentsapp.presentation.viewmodels.CommentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(
    postId: Int,
    onBack: () -> Unit,
    viewModel: CommentViewModel = hiltViewModel()
) {

    val comments by viewModel.comments.collectAsState()

    var author by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    // Cargar comentarios del post
    LaunchedEffect(postId) {
        viewModel.loadCommentsByPost(postId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comentarios") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp)
        ) {

            // ✍️ Formulario de comentario
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Autor") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                label = { Text("Comentario") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (author.isNotBlank() && comment.isNotBlank()) {
                        viewModel.addComment(
                            Comment(
                                id = 0, // auto generado por Room
                                postId = postId,
                                name = author,
                                body = comment,
                            )
                        )
                        author = ""
                        comment = ""
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Agregar")
            }

            Spacer(modifier = Modifier.height(14.dp))

            Divider()

            Spacer(modifier = Modifier.height(10.dp))

            // 📄 Lista de comentarios
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(comments) { comment ->
                    CommentItem(comment)
                }
            }
        }
    }
}
