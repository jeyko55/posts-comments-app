package com.jacobo.postscommentsapp.presentation.posts.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jacobo.postscommentsapp.presentation.screens.posts.components.PostItem
import com.jacobo.postscommentsapp.presentation.viewmodels.PostViewModel

@Composable
fun PostScreen(
    onPostClick: (Int) -> Unit,
    viewModel: PostViewModel = hiltViewModel()
) {
    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchPostsFromApi()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔍 Buscador
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.updateSearchQuery(it)
            },
            label = { Text("Buscar por título") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        // ⏳ Loader
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {

            // 📄 Lista de Posts
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(posts) { post ->
                    PostItem(
                        post = post,
                        onClick = { onPostClick(post.id) }
                    )
                }
            }
        }
    }
}
