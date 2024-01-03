package com.example.todoapp.di

import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.domain.mappers.TaskMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTaskRepository(taskDao: TaskDao, mapper: TaskMapper): TaskRepository {
        return TaskRepository(taskDao, mapper)
    }
}