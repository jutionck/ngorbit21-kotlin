package com.example.ngorbit21androidkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ngorbit21androidkotlin.data.repository.impl.TodoRepositoryImpl
import com.example.ngorbit21androidkotlin.domain.usecase.AddTodoUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.DeleteTodoUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.GetTodosUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.UpdateTodoUseCase
import com.example.ngorbit21androidkotlin.presentation.ui.screen.TodoDetailScreen
import com.example.ngorbit21androidkotlin.presentation.ui.screen.TodoListScreen
import com.example.ngorbit21androidkotlin.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val repository = TodoRepositoryImpl()
            val viewModel: TodoViewModel = viewModel {
                TodoViewModel(
                    GetTodosUseCase(repository),
                    AddTodoUseCase(repository),
                    DeleteTodoUseCase(repository),
                    UpdateTodoUseCase(repository)
                )
            }

            val navController = rememberNavController()
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
    }
}