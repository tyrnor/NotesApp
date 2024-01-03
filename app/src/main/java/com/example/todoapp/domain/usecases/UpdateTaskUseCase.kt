package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.domain.entities.Task
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.updateTask(task)
}