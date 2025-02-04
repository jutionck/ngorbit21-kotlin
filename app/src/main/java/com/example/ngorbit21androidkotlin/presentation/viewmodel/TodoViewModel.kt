package com.example.ngorbit21androidkotlin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ngorbit21androidkotlin.domain.model.Todo
import com.example.ngorbit21androidkotlin.domain.usecase.AddTodoUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.DeleteTodoUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.GetTodosUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : ViewModel() {

    private val _todoItems = MutableStateFlow<List<Todo>>(emptyList())
    val todoItems: StateFlow<List<Todo>> = _todoItems.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            getTodosUseCase().collect { todos ->
                _todoItems.value = todos
            }
        }
    }

    fun getTodoById(id: Int): Todo? {
        return todoItems.value.find { it.id == id }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            addTodoUseCase(todo)
            loadTodos()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
            loadTodos()
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            updateTodoUseCase(todo)
            loadTodos()
        }
    }
}