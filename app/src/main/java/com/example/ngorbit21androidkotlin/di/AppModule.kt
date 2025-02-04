package com.example.ngorbit21androidkotlin.di

import com.example.ngorbit21androidkotlin.data.repository.TodoRepository
import com.example.ngorbit21androidkotlin.data.repository.impl.TodoRepositoryImpl
import com.example.ngorbit21androidkotlin.domain.usecase.AddTodoUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.DeleteTodoUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.GetTodosUseCase
import com.example.ngorbit21androidkotlin.domain.usecase.UpdateTodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoRepository(): TodoRepository = TodoRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetTodosUseCase(repository: TodoRepository): GetTodosUseCase {
        return GetTodosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddTodoUseCase(repository: TodoRepository): AddTodoUseCase {
        return AddTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodoUseCase {
        return DeleteTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTodoUseCase(repository: TodoRepository): UpdateTodoUseCase {
        return UpdateTodoUseCase(repository)
    }
}