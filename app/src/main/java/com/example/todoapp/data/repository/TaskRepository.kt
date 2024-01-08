package com.example.todoapp.data.repository

import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.mappers.TaskMapper
import com.example.todoapp.domain.mappers.TaskMapperImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(private val taskDao: TaskDao, private val mapper: TaskMapper) {

    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks().map { entities ->
            entities.map(mapper::fromEntityToDomain)
        }
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(mapper.fromDomainToEntity(task))
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(mapper.fromDomainToEntity(task))
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(mapper.fromDomainToEntity(task))
    }

    fun getTaskById(id: Long): Flow<Task> {
        return taskDao.getTaskById(id).map(mapper::fromEntityToDomain)
    }
}