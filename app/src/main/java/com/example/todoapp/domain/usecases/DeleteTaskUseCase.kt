package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.domain.entities.Task
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.deleteTask(task)
}