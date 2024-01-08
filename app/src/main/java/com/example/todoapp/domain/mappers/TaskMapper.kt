package com.example.todoapp.domain.mappers

import com.example.todoapp.data.model.TaskEntity
import com.example.todoapp.domain.entities.IsIconsVisible
import com.example.todoapp.domain.entities.Task

interface TaskMapper {

    fun fromEntityToDomain(taskEntity: TaskEntity): Task {
        return Task(
            id = taskEntity.id,
            title = taskEntity.title,
            description = taskEntity.description,
            creationDate = taskEntity.creationDate,
        )
    }

    fun fromDomainToEntity(task: Task): TaskEntity {
        return TaskEntity(
            title = task.title,
            description = task.description,
            creationDate = task.creationDate,
        )
    }
}