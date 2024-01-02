package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.domain.entities.Task
import kotlinx.coroutines.flow.Flow

class GetTaskByIdUseCase(private val repository: TaskRepository) {
    operator fun invoke(id: Long): Flow<Task> = repository.getTaskById(id)
}