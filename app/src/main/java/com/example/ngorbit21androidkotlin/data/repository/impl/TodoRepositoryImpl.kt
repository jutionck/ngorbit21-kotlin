package com.example.ngorbit21androidkotlin.data.repository.impl

import com.example.ngorbit21androidkotlin.data.repository.TodoRepository
import com.example.ngorbit21androidkotlin.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoRepositoryImpl : TodoRepository {
    private val _todoList = MutableStateFlow(
        listOf(
            Todo(1, "Buy groceries"),
            Todo(2, "Walk the dog"),
            Todo(3, "Finish Kotlin project"),
            Todo(4, "Read a book")
        )
    )

    override fun getTodos(): Flow<List<Todo>> = _todoList.asStateFlow()

    override suspend fun addTodo(todo: Todo) {
        _todoList.value += todo
    }

    override suspend fun deleteTodo(todo: Todo) {
        _todoList.value = _todoList.value.filter { it.id != todo.id }
    }

    override suspend fun updateTodo(todo: Todo) {
        _todoList.value = _todoList.value.map {
            if (it.id == todo.id) todo else it
        }
    }
}