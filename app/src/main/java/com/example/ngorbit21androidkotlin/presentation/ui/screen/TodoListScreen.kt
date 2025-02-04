package com.example.ngorbit21androidkotlin.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ngorbit21androidkotlin.domain.model.Todo
import com.example.ngorbit21androidkotlin.presentation.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(navController: NavController, viewModel: TodoViewModel) {
    val todoItems by viewModel.todoItems.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            val newId = (todoItems.maxOfOrNull { it.id } ?: 0) + 1
            viewModel.addTodo(Todo(newId, "New Todo $newId"))
        }) {
            Text("Add Todo")
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
            items(todoItems) { item ->
                TodoListItem(todo = item, navController, viewModel)
            }
        }
    }
}

@Composable
fun TodoListItem(todo: Todo, navController: NavController, viewModel: TodoViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate("todoDetail/${todo.id}")
            },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(todo.title)
            Button(onClick = { viewModel.deleteTodo(todo) }) {
                Text("Delete")
            }
        }
    }
}