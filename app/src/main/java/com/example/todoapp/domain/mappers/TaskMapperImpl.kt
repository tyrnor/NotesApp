package com.example.todoapp.domain.mappers

import com.example.todoapp.data.model.TaskEntity
import com.example.todoapp.domain.entities.Task

class TaskMapperImpl : TaskMapper {
    override fun fromEntityToDomain(taskEntity: TaskEntity): Task {
        return Task(
            id = taskEntity.id,
            title = taskEntity.title,
            description = taskEntity.description,
            creationDate = taskEntity.creationDate,
        )
    }

    override fun fromDomainToEntity(task: Task): TaskEntity {
        return TaskEntity(
            id = task.id,
            title = task.title,
            description = task.description,
            creationDate = task.creationDate,
        )
    }
}