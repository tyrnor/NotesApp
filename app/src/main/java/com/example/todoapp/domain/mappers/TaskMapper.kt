package com.example.todoapp.domain.mappers

import com.example.todoapp.data.model.TaskEntity
import com.example.todoapp.domain.entities.Task

interface TaskMapper {

    fun fromEntityToDomain(taskEntity: TaskEntity): Task {
        return Task(
            id = taskEntity.id,
            title = taskEntity.title,
            description = taskEntity.description,
            dueDate = taskEntity.dueDate,
            isCompleted = taskEntity.isCompleted
        )
    }

    fun fromDomainToEntity(task: Task): TaskEntity {
        return TaskEntity(
            id = task.id,
            title = task.title,
            description = task.description,
            dueDate = task.dueDate,
            isCompleted = task.isCompleted
        )
    }
}