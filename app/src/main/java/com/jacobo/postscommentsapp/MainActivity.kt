package com.jacobo.postscommentsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.jacobo.postscommentsapp.presentation.navigation.AppNavGraph
import com.jacobo.postscommentsapp.presentation.theme.PostsCommentsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostsCommentsAppTheme {
                AppNavGraph()   // ✅ AQUÍ SE LLAMA LA NAVEGACIÓN
            }
        }
    }
}