package com.example.todoapp.domain.mappers

import com.example.todoapp.data.model.TaskEntity
import com.example.todoapp.domain.entities.IsIconsVisible
import com.example.todoapp.domain.entities.Task

class TaskMapperImpl : TaskMapper {
    override fun fromEntityToDomain(taskEntity: TaskEntity?): Task {
        if (taskEntity != null){
            return Task(
                id = taskEntity.id,
                title = taskEntity.title,
                description = taskEntity.description,
                creationDate = taskEntity.creationDate,
                isIconsVisible = IsIconsVisible.Hidden
            )
        }
        return Task(
            id = 0L,
            title = "",
            description = "",
            creationDate = null,
            isIconsVisible = IsIconsVisible.Hidden
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