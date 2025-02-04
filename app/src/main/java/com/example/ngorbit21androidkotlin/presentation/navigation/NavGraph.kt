package com.example.ngorbit21androidkotlin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ngorbit21androidkotlin.presentation.ui.screen.TodoDetailScreen
import com.example.ngorbit21androidkotlin.presentation.ui.screen.TodoListScreen
import com.example.ngorbit21androidkotlin.presentation.viewmodel.TodoViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val viewModel: TodoViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "todoList") {
        composable("todoList") {
            TodoListScreen(navController, viewModel)
        }
        composable("todoDetail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            TodoDetailScreen(id, viewModel)
        }
    }
}