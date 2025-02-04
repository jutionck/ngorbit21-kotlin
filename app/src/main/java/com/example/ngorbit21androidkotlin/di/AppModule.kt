package com.example.ngorbit21androidkotlin.di

import com.example.ngorbit21androidkotlin.data.repository.TodoRepository
import com.example.ngorbit21androidkotlin.data.repository.impl.TodoRepositoryImpl
import com.example.ngorbit21androidkotlin.domain.usecase.AddTodoUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.DeleteTodoUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.GetTodosUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.UpdateTodoUseCase
import com.example.ngorbit21androidkotlin.presentation.viewmodel.TodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<TodoRepository> { TodoRepositoryImpl() }

    factory { GetTodosUseCase(get()) }
    factory { AddTodoUseCase(get()) }
    factory { DeleteTodoUseCase(get()) }
    factory { UpdateTodoUseCase(get()) }

    viewModel {
        TodoViewModel(
            getTodosUseCase = get(),
            addTodoUseCase = get(),
            deleteTodoUseCase = get(),
            updateTodoUseCase = get()
        )
    }
}

