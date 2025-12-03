package com.jacobo.postscommentsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.jacobo.postscommentsapp.presentation.posts.screens.PostScreen
import com.jacobo.postscommentsapp.presentation.comments.screens.CommentScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.POSTS
    ) {

        composable(Routes.POSTS) {
            PostScreen(
                onPostClick = { postId ->
                    navController.navigate("${Routes.COMMENTS}/$postId")
                }
            )
        }

        composable(
            route = "${Routes.COMMENTS}/{postId}",
            arguments = listOf(
                navArgument("postId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val postId = backStackEntry.arguments?.getInt("postId") ?: 0

            CommentScreen(
                postId = postId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
