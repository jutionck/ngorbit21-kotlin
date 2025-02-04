package com.example.ngorbit21androidkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ngorbit21androidkotlin.ui.theme.NgorbIT21AndroidKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NgorbIT21AndroidKotlinTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "todolist") {
                    composable("todolist") { TodoListScreen(navController) }
                    composable("todoDetail/{title}") { backStackEntry ->
                        val title = backStackEntry.arguments?.getString("title") ?: "No Title"
                        TodoDetailScreen(title)
                    }
                }
            }
        }
    }
}

data class Todo(val title: String)

@Composable
fun TodoListScreen(navController: NavController, modifier: Modifier = Modifier) {
    val todos = remember { mutableStateListOf(
        Todo("Makan nasi goreng"),
        Todo("Minum kopi"),
        Todo("Belajar Kotlin"),
        Todo("NgorbIT#21"),
    ) }
    LazyColumn(modifier.padding(16.dp)) {
        items(todos) { item -> TodoListItem(todo = item, navController) }
    }
}

@Composable
fun TodoListItem(todo: Todo, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate("todoDetail/${todo.title}") }
    ) {
        Text(todo.title, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun TodoDetailScreen(title: String) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text("Todo Detail", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

//@Preview
//@Composable
//fun TodoPreview() {
//    NgorbIT21AndroidKotlinTheme {
//        TodoDetailScreen("Detail Todo List")
//    }
//}
