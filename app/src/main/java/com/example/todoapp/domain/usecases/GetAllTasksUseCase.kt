package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.domain.entities.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> = repository.getAllTasks()
}